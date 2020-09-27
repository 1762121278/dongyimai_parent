import com.offcn.entity.Result;
import com.offcn.group.Goods;
import com.offcn.pojo.TbSeller;
import com.offcn.sellergoods.service.GoodsService;
import com.offcn.shop.controller.GoodsController;
import com.offcn.shop.controller.SellerController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/springmvc.xml")
public class test {
    @Autowired
    private SellerController controller;

    @Autowired
    private GoodsController goodsController;

    @Test
    public void testFindAll(){
        TbSeller tbSeller = new TbSeller();
        tbSeller.setSellerId("阿聪啊");
        tbSeller.setName("wefwe");
        tbSeller.setPassword("123456");
        Result add = controller.add(tbSeller);
        System.out.println(add);
    }

    @Test
    public void add(){
        Goods goods = new Goods();
        goods.getTbgoods().setGoodsName("舒江涛");
        goods.getGoodsDesc().setPackageList("舒江涛");
        Result result = goodsController.add(goods);
        System.out.println(result);
    }
}
