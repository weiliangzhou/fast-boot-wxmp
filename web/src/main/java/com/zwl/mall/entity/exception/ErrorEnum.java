package com.zwl.mall.entity.exception;

/**
 * @author 二师兄超级帅
 * @Title:
 * @ProjectName mall
 * @Description: TODO
 * @date 2019/1/2614:05
 */
public enum ErrorEnum {

    //用户错误 100-800
    LoginError(100, "用户名或密码错误！"),
    UserNotExist(101, "用户不存在！"),
    CellphoneNotExist(102, "手机号码不存在！"),
    InvalidCode(103, "无效的验证码！"),
    NouAthorization(104, "无用户权限！"),
    InvalidAccessToken(105, "未登录或已过期！"),
    CellphoneUserExist(106, "该手机号已经被注册！"),
    UserAreadyHasCellphone(107, "该用户已经绑定过手机！"),
    UsernameExists(108, "用户名已经存在"),
    ChangePwdFail(109, "修改密码失败，请重试！"),
    RefUserNotExist(110, "推荐人手机号不存在！"),
    IsNotVendor(111, "该用户不是供应商，不能发布商品"),
    VipExpiresTimeError(112, "会员到期时间不能早于当前时间"),
    CellphoneIsExist(113, "手机号码已经被注册"),

    //VIP
    SetUserVipFailed(150, "设置用户VIP失败！"),
    BindTime(151, "该用户已被临时锁定 , 解锁之后再添加！"),

    //系统错误
    SmsError(810, "短信发送错误！"),
    InvalidWxCode(811, "微信验证错误！"),


    //1000-2000商品错误


    //2000-3000订单错误
    LimitBuy(2001, "线下购买次数受限！"),
    UNBINDOPENID(2002, "未绑定授权微信号！"),
    FullBuy(2003, "线下活动名额已满！"),
    AlreadyBuy(2004, "不能重复购买！"),
    InvalidCard(2005, "身份证格式不正确！"),
    ForbiddenBuy(2006, "VIP不需要购买！"),
    LimitBuyMsg(2007, "您已购买！"),
    LimitByRefUserIsNotVip(2008, "推荐人不是VIP，不可购买！"),
    SaveApplicationFormFail(2009, "报名信息提交失败！"),
    IsNotOnSale(2010, "该商品已下架"),
    MoreThanTime(2011, "开始时间不能大于结束时间"),
    OrderNotExist(2012, "订单不存在"),
    OrderSnNotNull(2013, "订单号不能为空"),

    //3000-4000分销系统错误
    NotLogin(3000, "无登录权限"),
    GoodsIdNotNull(3001, "商品编号不能为空"),
    SkuIdNotNull(3002, "SKU不能为空"),
    SerialType(3003, "请选择付款类型"),
    SerialNum(3004, "请输入流水号"),
    SerialImg(3005, "请输入流水凭证"),
    CategoryId(3006, "商品类目为空"),
    EffectDate(3007, "生效时间为空"),
    LevelNotNull(3008, "等级为空"),
    OrderNo(3009, "订单号为空"),
    OrderImg(3010, "凭证为空"),
    OrderStatus(3011, "订单状态错误"),
    AreaNull(3012, "区域为空"),
    DataAlready(3013, "数据已存在"),
    IsVip(3101, "该用户已经是会员"),
    IsAgent(3102, "该用户已经是代理"),
    CardIsNotEnough(3103, "代理商卡数不足"),
    LevelisNotExist(3103, "该代理商等级不存在"),
    AddressIsAlreadyExist(3104, "该用户已完善区域信息"),
    DateFormatError(3105, "日期格式错误"),
    LevelSign(3106, "该等级需要签约"),
    ParamsError(3200, "参数错误"),
    CellPhoneIsEmpty(3201, "手机号码为空"),
    UidIsEmpty(3202, "用户标识为空"),
    UpUidIsEmpty(3203, "上级标识不存在"),
    UpUidExist(3204, "上级用户已存在"),
    AgentLevelIsEmpty(3205, "用户等级为空"),
    PrefectureIsEmpty(3206, "区县为空"),
    ProvinceIsEmpty(3207, "省份为空"),
    CityIsEmpty(3208, "城市为空"),
    AddressIsEmpty(3209, "地址为空"),
    BuyUidIsEmpty(3300, "买方标识为空"),
    SaleUidIsEmpty(3301, "卖方标识为空"),
    SaleTypeIsEmpty(3302, "订单类型为空"),
    SalePriceIsEmpty(3303, "订单金额为空"),
    RemittanceImgIsEmpty(3304, "打款记录图片为空"),
    NumIsEmpty(3305, "卡数为空"),
    ReferrerLevelLow(3306, "推荐人等级过低"),
    ReferrerLevelUP(3307, "推荐人等级过高"),
    ReferrerLevelErr(3308, "推荐无效的等级"),
    ReferrerUid(3309, "推荐人标识为空"),
    InvalidState(3400, "无效状态"),
    ReferrerDispose(3401, "推荐已被处理"),
    RealNameIsEmpty(3402, "姓名为空"),
    InvalidUpUser(3403, "无效上级用户"),
    IdCardIsBind(3404, "身份证已绑定"),
    UserPushBid(3405, "用户被推荐，无法充卡"),
    VipOpenCard(3406, "VIP不能开卡"),
    BuyPriceIsEmpty(3500, "购买金额为空"),
    LevelIsEmpty(3501, "等级为空"),
    LevelNameIsEmpty(3502, "等级名称为空"),
    UpLevelIsEmpty(3503, "上级等级为空"),
    DescriptionIsEmpty(3504, "描述为空"),
    UpCondIsEmpty(3505, "升级条件为空"),
    CurrentNumIsEmpty(3506, "升级到本级的卡数为空"),
    ReplenishNumIsEmpty(3507, "每次补卡数量为空"),
    IsSignIsEmpty(3508, "是否是签约等级为空"),
    NotDeposit(3509, "未缴纳保证金"),
    DepositNotExist(3510, "定金记录为空"),
    AuitStateIsEmpty(3511, "审核状态为空"),
    UserUpNotExist(3512, "用户上级为空"),
    DepositMoneyIsEmpty(3513, "定金为空"),
    PaymentTypeIsEmpty(3514, "支付类型为空"),
    SynError(3515, "同步出错"),
    ReplenishUp(3516, "充卡升级出错!隔级才能补卡升级"),
    BankCardNoIsEmpty(3517, "用户银行卡为空"),
    UserActive(3518, "用户已激活"),
    ProductIdIsNull(3519, "商品id为空"),
    CategoryIdIsNull(3520, "类目编号为空"),
    ProductTypeIsNull(3521, "商品类型为空"),
    IsAlloIsNull(3522, "是否参与分佣为空"),
    GoodsIsNotExist(3523, "商品不存在"),
    OnlinePayIsNull(3524, "支付方式为空"),
    IsShowIsNull(3525, "课程状态为空"),
    TopLineIsNull(3526, "课程排序"),


    //5000-6000 支付错误
    ERRORSIGN(5000, "支付签名错误"),
    ERRORMONEY(5001, "支付金额错误"),

    //6000-7000 支付错误
    ALREADYUSED(6000, "二维码使用次数已满，请更换"),

    //7001-8000 评论
    RepeatComment(7001, "已经发表过评论了！"),
    NoStarsError(7002, "评论不能为空！"),
    NoBuyComment(7003, "您还没评论权限！"),
    //8001-8010 导出excel
    MoreThan5000(8001, "导出条数大于5000,请分页"),

    //系统错误
    UnknownError(10000, "未知错误！"),
    ArgumentError(10001, "参数错误！"),
    WxError(10002, "微信返回错误！"),
    OPERATION_TOO_QUICK(10003, "操作太过频繁！"),
    DataError(10004, "数据错误！"),
    DefaultReferrer(10005, "请后台配置默认邀请人！"),
    MerchantMiss(10006, "商户号不存在"),
    FailUpdate(10007, "更新失败"),
    MoreThan30Days(10008, "搜索时间不能超过30天"),
    TimeLessThan1(10009, "记录时间不能小于1秒");


    private int code;
    private String errmsg;

    ErrorEnum(int code, String errmsg) {
        this.code = code;
        this.errmsg = errmsg;
    }

    ;

    public int getCode() {
        return code;
    }

    public String getErrmsg() {
        return errmsg;
    }
}
