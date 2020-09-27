package com.offcn.search.service;

import com.offcn.pojo.TbItem;

import java.util.List;
import java.util.Map;

public interface ItemSearchService {

    public Map<String,Object> search(Map searchMap);

    public void importList(List<TbItem> list);
    /**
     * 删除数据
     * @param ids
     */
    public void deleteByGoodsIds(List goodsIdList);


}
