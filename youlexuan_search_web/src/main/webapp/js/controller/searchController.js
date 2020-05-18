app.controller('searchController',function($scope,searchService,$location){

    //加载查询字符串 添加location服务用于接收参数
    $scope.loadkeywords = function () {
        $scope.searchMap.keywords = $location.search()['keywords'];
        $scope.search();
    }

    //搜索
    $scope.search=function(){
        // 在执行查询前，转换为int类型，否则提交到后端有可能变成字符串
        $scope.searchMap.pageNo = parseInt($scope.searchMap.pageNo);
        searchService.search( $scope.searchMap ).success(
            function(response){
                $scope.resultMap=response;//搜索返回的结果
                buildPageLabel();
            }
        );
    }

    //搜索对象
    $scope.searchMap = {'keywords': '', 'category': '', 'brand': '', 'spec': {},'price':'','pageNo':1,'pageSize':10, 'sortField':'', 'sort':'' };
    //添加搜索项
    $scope.addSearchItem = function (key, value) {
        if (key == 'category' || key == 'brand'|| key=='price') {//如果点击的是分类或者是品牌
            $scope.searchMap[key] = value;
        } else {
            $scope.searchMap.spec[key] = value;
        }

        $scope.search();//执行搜索
    }
    //移除复合搜索条件
    $scope.removeSearchItem = function (key) {
        if (key == "category" || key == "brand"|| key=='price') {//如果是分类或品牌
            $scope.searchMap[key] = "";
        } else {//否则是规格
            delete $scope.searchMap.spec[key];//移除此属性
        }
        $scope.search();//执行搜索
    }

    //构建分页标签(totalPages为总页数)
    buildPageLabel = function () {
        $scope.pageLabel = [];//页面中会遍历这个数组来获取页码,即1,2,3,4,5等页码
        var maxPageNo = $scope.resultMap.totalPages;//得到总页码(以搜索手机为例,共21页)
        var firstPage = 1;//开始页码
        var lastPage = maxPageNo;//结束页码

        $scope.firstDot = true;//前面有点
        $scope.lastDot = true;//后边有点

        if ($scope.resultMap.totalPages > 5) {  //如果总页数大于5页
            if ($scope.searchMap.pageNo <= 3) {//如果当前页小于等于3
                lastPage = 5; //最大显示页码为5, 即1 2 3 4 5
                $scope.firstDot = false;//前面没点
            } else if ($scope.searchMap.pageNo >= lastPage - 2) { // 若选择了 19或20或21页, 起始页码显示16. 即16 17 18 19 20 21
                firstPage = maxPageNo - 4;     //后5页
                $scope.lastDot = false;//后边没点
            } else { //若选择了 4~18之间的页码, 则以当前页码增减2, 如选了10页  则显示8 9 10 11 12 13
                firstPage = $scope.searchMap.pageNo - 2;
                lastPage = $scope.searchMap.pageNo + 2;
            }
        }else {
            $scope.firstDot = false;//前面无点
            $scope.lastDot = false;//后边无点
        }
        //循环产生页码标签
        for (var i = firstPage; i <= lastPage; i++) {
            $scope.pageLabel.push(i);
        }
    }
    //根据页码查询
    $scope.queryByPage = function (pageNo) {
        //页码验证
        if (pageNo < 1 || pageNo > $scope.resultMap.totalPages) {
            return;
        }
        $scope.searchMap.pageNo = pageNo;
        $scope.search();
    }

    //判断当前页为第一页
    $scope.isTopPage = function () {
        if ($scope.searchMap.pageNo == 1) {
            return true;
        } else {
            return false;
        }
    }

    //判断当前页是否未最后一页
    $scope.isEndPage = function () {
        if ($scope.searchMap.pageNo == $scope.resultMap.totalPages) {
            return true;
        } else {
            return false;
        }
    }

    //判断指定页码是否是当前页
    $scope.ispage=function (p) {
        if(parseInt(p)==parseInt($scope.searchMap.pageNo)){
            return true;
        }else {
            return false;
        }
    }
    //设置排序规则
    $scope.sortSearch=function(sortField,sort){
        $scope.searchMap.sortField=sortField;
        $scope.searchMap.sort=sort;
        $scope.search();
    }

    //判断关键字是不是品牌
    $scope.keywordsIsBrand = function () {
        for (var i = 0; i < $scope.resultMap.brandList.length; i++) {
            if ($scope.searchMap.keywords.indexOf($scope.resultMap.brandList[i].text) >= 0) {//如果包含
                return true;
            }
        }
        return false;
    }
});