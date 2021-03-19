package 高并发.提高接口效率;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.*;

public class Demo1 {

    /**
     * 获取商品基本信息
     *
     * @param goodsId 商品id
     * @return 商品基本信息
     * @throws InterruptedException
     */
    public String goodsDetailModel(long goodsId) throws InterruptedException {
        //模拟耗时，休眠200ms
        TimeUnit.MILLISECONDS.sleep(200);
        return "商品id:" + goodsId + ",商品基本信息....";
    }

    /**
     * 获取商品图片列表
     *
     * @param goodsId 商品id
     * @return 商品图片列表
     * @throws InterruptedException
     */
    public List<String> goodsImgsModelList(long goodsId) throws InterruptedException {
        //模拟耗时，休眠200ms
        TimeUnit.MILLISECONDS.sleep(200);
        return Arrays.asList("图1", "图2", "图3");
    }

    /**
     * 获取商品描述信息
     *
     * @param goodsId 商品id
     * @return 商品描述信息
     * @throws InterruptedException
     */
    public String goodsExtModel(long goodsId) throws InterruptedException {
        //模拟耗时，休眠200ms
        TimeUnit.MILLISECONDS.sleep(200);
        return "商品id:" + goodsId + ",商品描述信息......";
    }

    //创建个线程池
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    /**
     * 获取商品详情
     *
     * @param goodsId 商品id
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public Map<String, Object> goodsDetail(long goodsId) throws ExecutionException, InterruptedException {
        Map<String, Object> result = new HashMap<>();

        //异步获取商品基本信息
        Future<String> gooldsDetailModelFuture = executorService.submit(() -> goodsDetailModel(goodsId));
        //异步获取商品图片列表
        Future<List<String>> goodsImgsModelListFuture = executorService.submit(() -> goodsImgsModelList(goodsId));
        //异步获取商品描述信息
        Future<String> goodsExtModelFuture = executorService.submit(() -> goodsExtModel(goodsId));

        result.put("gooldsDetailModel", gooldsDetailModelFuture.get());
        result.put("goodsImgsModelList", goodsImgsModelListFuture.get());
        result.put("goodsExtModel", goodsExtModelFuture.get());
        return result;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(monotoneIncreasingDigits(332));
    }

    public static int monotoneIncreasingDigits(int N) {
        if(N<10){
            return N;
        }
        char[] number = String.valueOf(N).toCharArray();
        int max = -1,idx = -1;
        for(int i = 0 ; i<number.length-1; i++){
            if(max < number[i]){
                max = number[i];
                idx = i;
            }
            if(number[i]>number[i+1]){
                number[idx] -= 1;
                for(int j = idx+1;j<number.length;j++){
                    number[j] = '9';
                }
                break;
            }
        }
        return Integer.parseInt(new String(number));
    }

}