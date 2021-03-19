package sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import sql.Test1.*;

public class Test2 {

    public static int joinBufferSize = 10000;
    public static List<?> joinBufferList = new ArrayList<>();

    public static <R1, R2> List<Test1.Record<R1, R2>> join(List<R1> table1, List<R2> table2, Test1.JoinType joinType, Filter<R1, R2> onFilter, Filter<R1, R2> whereFilter) {
        if (Objects.isNull(table1) || Objects.isNull(table2) || joinType == null) {
            return new ArrayList<>();
        }

        List<Test1.Record<R1, R2>> result = new CopyOnWriteArrayList<>();

        int table1Size = table1.size();
        int fromIndex = 0, toIndex = joinBufferSize;
        toIndex = Integer.min(table1Size, toIndex);
        while (fromIndex < table1Size && toIndex <= table1Size) {
            joinBufferList = table1.subList(fromIndex, toIndex);
            fromIndex = toIndex;
            toIndex += joinBufferSize;
            toIndex = Integer.min(table1Size, toIndex);

            List<Record<R1, R2>> blockNestedLoopResult = blockNestedLoop((List<R1>) joinBufferList, table2, onFilter);
            result.addAll(blockNestedLoopResult);
        }

        if (joinType == JoinType.leftJoin) {
            List<R1> r1Record = result.stream().map(Record::getR1).collect(Collectors.toList());
            List<Record<R1, R2>> leftAppendList = new ArrayList<>();
            for (R1 r1 : table1) {
                if (!r1Record.contains(r1)) {
                    leftAppendList.add(Record.build(r1, null));
                }
            }
            result.addAll(leftAppendList);
        }
        if (Objects.nonNull(whereFilter)) {
            for (Record<R1, R2> record : result) {
                if (!whereFilter.accept(record.r1, record.r2)) {
                    result.remove(record);
                }
            }
        }
        return result;
    }

    public static <R1, R2> List<Record<R1, R2>> blockNestedLoop(List<R1> joinBufferList, List<R2> table2, Filter<R1, R2> onFilter) {
        List<Record<R1, R2>> result = new ArrayList<>();
        for (R2 r2 : table2) {
            for (R1 r1 : joinBufferList) {
                if (Objects.nonNull(onFilter) ? onFilter.accept(r1, r2) : true) {
                    result.add(Record.build(r1, r2));
                }
            }
        }
        return result;
    }

    @Test
    public void innerJoin() {
        List<Table1> table1 = Arrays.asList(Table1.build(1), Table1.build(2), Table1.build(3));
        List<Table2> table2 = Arrays.asList(Table2.build(3), Table2.build(4), Table2.build(5));

        join(table1, table2, JoinType.innerJoin, null, null).forEach(System.out::println);
        System.out.println("-----------------");
        join(table1, table2, JoinType.innerJoin, (r1, r2) -> r1.a == r2.b, null).forEach(System.out::println);
    }

    @Test
    public void leftJoin() {
        List<Table1> table1 = Arrays.asList(Table1.build(1), Table1.build(2), Table1.build(3));
        List<Table2> table2 = Arrays.asList(Table2.build(3), Table2.build(4), Table2.build(5));

        join(table1, table2, JoinType.leftJoin, (r1, r2) -> r1.a == r2.b, null).forEach(System.out::println);
        System.out.println("-----------------");
        join(table1, table2, JoinType.leftJoin, (r1, r2) -> r1.a > 10, null).forEach(System.out::println);
    }
}