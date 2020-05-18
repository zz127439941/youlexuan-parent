package com.offcn.sellergoods.service;
import java.util.List;
import java.util.Map;

import com.offcn.entity.PageResult;
import com.offcn.pojo.TbBrand;
/**
 * 品牌服务层接口
 * @author Administrator
 *
 */
public interface BrandService {
    /**
     * 返回全部列表
     * @return
     */
    public List<TbBrand> findAll();


    public PageResult findPage(int pageNum,int pageSize);


    public void add(TbBrand brand);

    public TbBrand findOne(Long id);

    public void update(TbBrand brand);

    public void delete(Long [] ids);

    public PageResult findPage(TbBrand brand, int pageNum,int pageSize);

    public List<Map> selectOptionList();
}