package com.jk.luckydraw.service.prize;

import com.jk.luckydraw.domain.prize.PrizeBean;

import java.util.List;

public interface PrizeService {
    List<PrizeBean> findPrizeList();

    int findPrizeCount();

    void savePrize(PrizeBean prizeBean);

    void delPrize(Integer[] ids);
}
