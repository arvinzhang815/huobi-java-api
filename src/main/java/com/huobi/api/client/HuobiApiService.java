package com.huobi.api.client;

import com.huobi.api.client.constant.HuobiConsts;
import com.huobi.api.client.domain.*;
import com.huobi.api.client.domain.resp.RespBody;
import com.huobi.api.client.domain.resp.RespTick;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Set;

/**
 * created by jacky. 2018/7/20 8:41 PM
 */
public interface HuobiApiService {


    @GET("/market/history/kline")
    Call<RespBody<Set<Kline>>> kline(@Query("symbol") String symbol, @Query("period") String period, @Query("size") int size);


    @GET("/market/detail/merged")
    Call<RespBody<Merged>> merged(@Query("symbol") String symbol);

    @GET("/market/tickers")
    Call<RespBody<Set<Kline>>> tickers();

    @GET("/market/depth")
    Call<RespBody<Depth>> depth(@Query("symbol") String symbol, @Query("type") String type);


    @GET("/market/trade")
    Call<RespBody<RespTick<Set<Trade>>>> trade(@Query("symbol") String symbol);

    @GET("/market/history/trade")
    Call<RespBody<Trade>> historyTrade(@Query("symbol") String symbol, @Query("size") int size);

    @GET("/market/detail")
    Call<RespBody<Kline>> detail(@Query("symbol") String symbol);


    @GET("/v1/common/symbols")
    Call<RespBody<Set<Symbol>>> symbols();

    @GET("/v1/common/currencys")
    Call<RespBody<Set<String>>> currencys();

    @GET("/v1/common/timestamp")
    Call<Long> timestamp();

    @GET("/v1/account/accounts")
    Call<RespBody<Set<AccountStates>>> accounts(@Query("id") String id, @Query("state") String state, @Query("type") String type);

    /**
     * @param symbol
     * @param types
     * @param startDate
     * @param endDate
     * @param states
     * @param from
     * @param direct    prev,next
     * @param size
     * @return
     */
    @Headers(HuobiConsts.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v1/order/orders")
    Call<RespBody<Set<Order>>> orders(@Query("symbol") String symbol, @Query("types") String types,
                                      @Query("startDate") String startDate, @Query("endDate") String endDate,
                                      @Query("states") String states,
                                      @Query("from") String from, @Query("direct") String direct, @Query("states") String size);


    @GET("/v1/account/accounts/{account-id}/balance")
    Call<RespBody<Account>> balance(@Path("account-id") String accountId);


    @POST("/v1/order/orders/place")
    Call<RespBody<Long>> place(@Query("account-id") String accountId, @Query("amount") String amount, @Query("price") String price,
                               @Query("source") String source, @Query("symbol") String symbol, @Query("type") String type);


    @GET("/v1/order/openOrders")
    Call<RespBody<Set<Order>>> openOrders(@Query("account-id") String accountId, @Query("symbol") String symbol, @Query("side") String side, @Query("size") int size);


    @GET("/v1/order/orders/{order-id}")
    Call<RespBody<Order>> order(@Query("order-id") String orderId);

    @POST("/v1/order/orders/{order-id}/submitcancel")
    Call<RespBody<Long>> cancel(@Query("order-id") String orderId);

    @GET("/v1/order/orders/{order-id}/matchresults")
    Call<RespBody<Set<Order>>> matchresults(@Query("order-id") String orderId);


    @GET("/v1/margin/accounts/balance")
    Call<RespBody<Set<Order>>> marginBalance(@Query("symbol") String symbol);


}