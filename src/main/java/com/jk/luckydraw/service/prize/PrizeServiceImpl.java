package com.jk.luckydraw.service.prize;

import com.jk.luckydraw.domain.prize.PrizeBean;
import com.jk.luckydraw.mapper.prize.PrizeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrizeServiceImpl implements PrizeService {

    @Autowired
    private PrizeMapper prizeMapper;

    @Override
    public List<PrizeBean> findPrizeList() {
        return prizeMapper.findPrizeList();
    }

    @Override
    public int findPrizeCount() {
        return prizeMapper.findPrizeCount();
    }

    @Override
    public void savePrize(PrizeBean prizeBean) {
        prizeMapper.savePrize(prizeBean);
    }

    @Override
    public void delPrize(Integer[] ids) {
        prizeMapper.delPrize(ids);
    }
}
