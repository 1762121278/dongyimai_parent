package com.offcn.solrutil;

import com.alibaba.fastjson.JSON;
import com.github.promeg.pinyinhelper.Pinyin;
import com.offcn.mapper.TbItemMapper;
import com.offcn.pojo.TbItem;
import com.offcn.pojo.TbItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SolrUtil {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private SolrTemplate solrTemplate;


    /**
     * 导入所有审核成功的商品信息到索引库
     */
    public void importItemDate(){

        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
//查询审核成功的信息
        criteria.andStatusEqualTo("1");

        List<TbItem> itemList = itemMapper.selectByExample(example);
        System.out.println("商品列表：");
        for (TbItem tbItem:itemList){
            System.out.println(tbItem.getTitle());
//首先取出规格数据，并且进行转换 将json字符串转换成map集合
            Map<String,String> specMap = JSON.parseObject(tbItem.getSpec(), Map.class);


//创建新的map存放拼音
            Map<String, String> mapPinYin = new HashMap<String, String>();

            for (String key : specMap.keySet()) {
                mapPinYin.put(Pinyin.toPinyin(key,"").toLowerCase(),specMap.get(key));
            }
            tbItem.setSpecMap(mapPinYin);
        }

        solrTemplate.saveBeans(itemList);
        solrTemplate.commit();

        System.out.println("导入结束");

    }

    public static void main(String[] args) {
//加载配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/application*.xml");

        SolrUtil solrUtil = (SolrUtil)context.getBean("solrUtil");
        solrUtil.importItemDate();
    }
}