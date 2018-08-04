package me.chanjar.weixin.mp.bean.membercard;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hasee on 2017/12/12.
 */
@Data
public class WxMpMemberCardAddMessage implements Serializable {

  /**
   * card_type : MEMBER_CARD
   * member_card : {"background_pic_url":"https://mmbiz.qlogo.cn/mmbiz/","base_info":{"logo_url":"http://mmbiz.qpic.cn/mmbiz/iaL1LJM1mF9aRKPZ/0","brand_name":"海底捞","code_type":"CODE_TYPE_TEXT","title":"海底捞会员卡","color":"Color010","notice":"使用时向服务员出示此券","service_phone":"020-88888888","description":"不可与其他优惠同享","date_info":{"type":"DATE_TYPE_PERMANENT"},"sku":{"quantity":50000000},"get_limit":3,"use_custom_code":false,"can_give_friend":true,"location_id_list":[123,12321],"custom_url_name":"立即使用","custom_url":"http://weixin.qq.com","custom_url_sub_title":"6个汉字tips","promotion_url_name":"营销入口1","promotion_url":"http://www.qq.com","need_push_on_view":true},"advanced_info":{"use_condition":{"accept_category":"鞋类","reject_category":"阿迪达斯","can_use_with_other_discount":true},"abstract":{"abstract":"微信餐厅推出多种新季菜品，期待您的光临","icon_url_list":["http://mmbiz.qpic.cn/mmbiz/p98FjXy8LacgHxp3sJ3vn97bGLz0ib0Sfz1bjiaoOYA027iasqSG0sj  piby4vce3AtaPu6cIhBHkt6IjlkY9YnDsfw/0"]},"text_image_list":[{"image_url":"http://mmbiz.qpic.cn/mmbiz/p98FjXy8LacgHxp3sJ3vn97bGLz0ib0Sfz1bjiaoOYA027iasqSG0sjpiby4vce3AtaPu6cIhBHkt6IjlkY9YnDsfw/0","text":"此菜品精选食材，以独特的烹饪方法，最大程度地刺激食 客的味蕾"},{"image_url":"http://mmbiz.qpic.cn/mmbiz/p98FjXy8LacgHxp3sJ3vn97bGLz0ib0Sfz1bjiaoOYA027iasqSG0sj piby4vce3AtaPu6cIhBHkt6IjlkY9YnDsfw/0","text":"此菜品迎合大众口味，老少皆宜，营养均衡"}],"time_limit":[{"type":"MONDAY","begin_hour":0,"end_hour":10,"begin_minute":10,"end_minute":59},{"type":"HOLIDAY"}],"business_service":["BIZ_SERVICE_FREE_WIFI","BIZ_SERVICE_WITH_PET","BIZ_SERVICE_FREE_PARK","BIZ_SERVICE_DELIVER"]},"supply_bonus":true,"supply_balance":false,"prerogative":"test_prerogative","auto_activate":true,"custom_field1":{"name_type":"FIELD_NAME_TYPE_LEVEL","url":"http://www.qq.com"},"activate_url":"http://www.qq.com","custom_cell1":{"name":"使用入口2","tips":"激活后显示","url":"http://www.xxx.com"},"bonus_rule":{"cost_money_unit":100,"increase_bonus":1,"max_increase_bonus":200,"init_increase_bonus":10,"cost_bonus_unit":5,"reduce_money":100,"least_money_to_use_bonus":1000,"max_reduce_bonus":50},"discount":10}
   */

  private String card_type="MEMBER_CARD";
  private MemberCardBean member_card;

  public String getCard_type() {
    return card_type;
  }

  public void setCard_type(String card_type) {
    this.card_type = card_type;
  }

  public MemberCardBean getMember_card() {
    return member_card;
  }

  public void setMember_card(MemberCardBean member_card) {
    this.member_card = member_card;
  }

  public static class MemberCardBean {
    /**
     * background_pic_url : https://mmbiz.qlogo.cn/mmbiz/
     * base_info : {"logo_url":"http://mmbiz.qpic.cn/mmbiz/iaL1LJM1mF9aRKPZ/0","brand_name":"海底捞","code_type":"CODE_TYPE_TEXT","title":"海底捞会员卡","color":"Color010","notice":"使用时向服务员出示此券","service_phone":"020-88888888","description":"不可与其他优惠同享","date_info":{"type":"DATE_TYPE_PERMANENT"},"sku":{"quantity":50000000},"get_limit":3,"use_custom_code":false,"can_give_friend":true,"location_id_list":[123,12321],"custom_url_name":"立即使用","custom_url":"http://weixin.qq.com","custom_url_sub_title":"6个汉字tips","promotion_url_name":"营销入口1","promotion_url":"http://www.qq.com","need_push_on_view":true}
     * advanced_info : {"use_condition":{"accept_category":"鞋类","reject_category":"阿迪达斯","can_use_with_other_discount":true},"abstract":{"abstract":"微信餐厅推出多种新季菜品，期待您的光临","icon_url_list":["http://mmbiz.qpic.cn/mmbiz/p98FjXy8LacgHxp3sJ3vn97bGLz0ib0Sfz1bjiaoOYA027iasqSG0sj  piby4vce3AtaPu6cIhBHkt6IjlkY9YnDsfw/0"]},"text_image_list":[{"image_url":"http://mmbiz.qpic.cn/mmbiz/p98FjXy8LacgHxp3sJ3vn97bGLz0ib0Sfz1bjiaoOYA027iasqSG0sjpiby4vce3AtaPu6cIhBHkt6IjlkY9YnDsfw/0","text":"此菜品精选食材，以独特的烹饪方法，最大程度地刺激食 客的味蕾"},{"image_url":"http://mmbiz.qpic.cn/mmbiz/p98FjXy8LacgHxp3sJ3vn97bGLz0ib0Sfz1bjiaoOYA027iasqSG0sj piby4vce3AtaPu6cIhBHkt6IjlkY9YnDsfw/0","text":"此菜品迎合大众口味，老少皆宜，营养均衡"}],"time_limit":[{"type":"MONDAY","begin_hour":0,"end_hour":10,"begin_minute":10,"end_minute":59},{"type":"HOLIDAY"}],"business_service":["BIZ_SERVICE_FREE_WIFI","BIZ_SERVICE_WITH_PET","BIZ_SERVICE_FREE_PARK","BIZ_SERVICE_DELIVER"]}
     * supply_bonus : true
     * supply_balance : false
     * prerogative : test_prerogative
     * auto_activate : true
     * custom_field1 : {"name_type":"FIELD_NAME_TYPE_LEVEL","url":"http://www.qq.com"}
     * activate_url : http://www.qq.com
     * custom_cell1 : {"name":"使用入口2","tips":"激活后显示","url":"http://www.xxx.com"}
     * bonus_rule : {"cost_money_unit":100,"increase_bonus":1,"max_increase_bonus":200,"init_increase_bonus":10,"cost_bonus_unit":5,"reduce_money":100,"least_money_to_use_bonus":1000,"max_reduce_bonus":50}
     * discount : 10
     */

    private String background_pic_url;
    private BaseInfoBean base_info;
    private AdvancedInfoBean advanced_info;
    private boolean supply_bonus;
    private boolean supply_balance;
    private String prerogative;
    private boolean auto_activate;
    private CustomField1Bean custom_field1;
    private String activate_url;
    private CustomCell1Bean custom_cell1;
    private BonusRuleBean bonus_rule;
    private int discount;

    public String getBackground_pic_url() {
      return background_pic_url;
    }

    public void setBackground_pic_url(String background_pic_url) {
      this.background_pic_url = background_pic_url;
    }

    public BaseInfoBean getBase_info() {
      return base_info;
    }

    public void setBase_info(BaseInfoBean base_info) {
      this.base_info = base_info;
    }

    public AdvancedInfoBean getAdvanced_info() {
      return advanced_info;
    }

    public void setAdvanced_info(AdvancedInfoBean advanced_info) {
      this.advanced_info = advanced_info;
    }

    public boolean isSupply_bonus() {
      return supply_bonus;
    }

    public void setSupply_bonus(boolean supply_bonus) {
      this.supply_bonus = supply_bonus;
    }

    public boolean isSupply_balance() {
      return supply_balance;
    }

    public void setSupply_balance(boolean supply_balance) {
      this.supply_balance = supply_balance;
    }

    public String getPrerogative() {
      return prerogative;
    }

    public void setPrerogative(String prerogative) {
      this.prerogative = prerogative;
    }

    public boolean isAuto_activate() {
      return auto_activate;
    }

    public void setAuto_activate(boolean auto_activate) {
      this.auto_activate = auto_activate;
    }

    public CustomField1Bean getCustom_field1() {
      return custom_field1;
    }

    public void setCustom_field1(CustomField1Bean custom_field1) {
      this.custom_field1 = custom_field1;
    }

    public String getActivate_url() {
      return activate_url;
    }

    public void setActivate_url(String activate_url) {
      this.activate_url = activate_url;
    }

    public CustomCell1Bean getCustom_cell1() {
      return custom_cell1;
    }

    public void setCustom_cell1(CustomCell1Bean custom_cell1) {
      this.custom_cell1 = custom_cell1;
    }

    public BonusRuleBean getBonus_rule() {
      return bonus_rule;
    }

    public void setBonus_rule(BonusRuleBean bonus_rule) {
      this.bonus_rule = bonus_rule;
    }

    public int getDiscount() {
      return discount;
    }

    public void setDiscount(int discount) {
      this.discount = discount;
    }

    public static class BaseInfoBean {
      /**
       * logo_url : http://mmbiz.qpic.cn/mmbiz/iaL1LJM1mF9aRKPZ/0
       * brand_name : 海底捞
       * code_type : CODE_TYPE_TEXT
       * title : 海底捞会员卡
       * color : Color010
       * notice : 使用时向服务员出示此券
       * service_phone : 020-88888888
       * description : 不可与其他优惠同享
       * date_info : {"type":"DATE_TYPE_PERMANENT"}
       * sku : {"quantity":50000000}
       * get_limit : 3
       * use_custom_code : false
       * can_give_friend : true
       * location_id_list : [123,12321]
       * custom_url_name : 立即使用
       * custom_url : http://weixin.qq.com
       * custom_url_sub_title : 6个汉字tips
       * promotion_url_name : 营销入口1
       * promotion_url : http://www.qq.com
       * need_push_on_view : true
       */

      private String logo_url;
      private String brand_name;
      private String code_type;
      private String title;
      private String color;
      private String notice;
      private String service_phone;
      private String description;
      private DateInfoBean date_info;
      private SkuBean sku;
      private int get_limit;
      private boolean use_custom_code;
      private boolean can_give_friend;
      private String custom_url_name;
      private String custom_url;
      private String custom_url_sub_title;
      private String promotion_url_name;
      private String promotion_url;
      private boolean need_push_on_view;
      private List<Integer> location_id_list;

      public String getLogo_url() {
        return logo_url;
      }

      public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
      }

      public String getBrand_name() {
        return brand_name;
      }

      public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
      }

      public String getCode_type() {
        return code_type;
      }

      public void setCode_type(String code_type) {
        this.code_type = code_type;
      }

      public String getTitle() {
        return title;
      }

      public void setTitle(String title) {
        this.title = title;
      }

      public String getColor() {
        return color;
      }

      public void setColor(String color) {
        this.color = color;
      }

      public String getNotice() {
        return notice;
      }

      public void setNotice(String notice) {
        this.notice = notice;
      }

      public String getService_phone() {
        return service_phone;
      }

      public void setService_phone(String service_phone) {
        this.service_phone = service_phone;
      }

      public String getDescription() {
        return description;
      }

      public void setDescription(String description) {
        this.description = description;
      }

      public DateInfoBean getDate_info() {
        return date_info;
      }

      public void setDate_info(DateInfoBean date_info) {
        this.date_info = date_info;
      }

      public SkuBean getSku() {
        return sku;
      }

      public void setSku(SkuBean sku) {
        this.sku = sku;
      }

      public int getGet_limit() {
        return get_limit;
      }

      public void setGet_limit(int get_limit) {
        this.get_limit = get_limit;
      }

      public boolean isUse_custom_code() {
        return use_custom_code;
      }

      public void setUse_custom_code(boolean use_custom_code) {
        this.use_custom_code = use_custom_code;
      }

      public boolean isCan_give_friend() {
        return can_give_friend;
      }

      public void setCan_give_friend(boolean can_give_friend) {
        this.can_give_friend = can_give_friend;
      }

      public String getCustom_url_name() {
        return custom_url_name;
      }

      public void setCustom_url_name(String custom_url_name) {
        this.custom_url_name = custom_url_name;
      }

      public String getCustom_url() {
        return custom_url;
      }

      public void setCustom_url(String custom_url) {
        this.custom_url = custom_url;
      }

      public String getCustom_url_sub_title() {
        return custom_url_sub_title;
      }

      public void setCustom_url_sub_title(String custom_url_sub_title) {
        this.custom_url_sub_title = custom_url_sub_title;
      }

      public String getPromotion_url_name() {
        return promotion_url_name;
      }

      public void setPromotion_url_name(String promotion_url_name) {
        this.promotion_url_name = promotion_url_name;
      }

      public String getPromotion_url() {
        return promotion_url;
      }

      public void setPromotion_url(String promotion_url) {
        this.promotion_url = promotion_url;
      }

      public boolean isNeed_push_on_view() {
        return need_push_on_view;
      }

      public void setNeed_push_on_view(boolean need_push_on_view) {
        this.need_push_on_view = need_push_on_view;
      }

      public List<Integer> getLocation_id_list() {
        return location_id_list;
      }

      public void setLocation_id_list(List<Integer> location_id_list) {
        this.location_id_list = location_id_list;
      }

      public static class DateInfoBean {
        /**
         * type : DATE_TYPE_PERMANENT
         */

        private String type;

        public String getType() {
          return type;
        }

        public void setType(String type) {
          this.type = type;
        }
      }

      public static class SkuBean {
        /**
         * quantity : 50000000
         */

        private int quantity;

        public int getQuantity() {
          return quantity;
        }

        public void setQuantity(int quantity) {
          this.quantity = quantity;
        }
      }
    }

    public static class AdvancedInfoBean {
      /**
       * use_condition : {"accept_category":"鞋类","reject_category":"阿迪达斯","can_use_with_other_discount":true}
       * abstract : {"abstract":"微信餐厅推出多种新季菜品，期待您的光临","icon_url_list":["http://mmbiz.qpic.cn/mmbiz/p98FjXy8LacgHxp3sJ3vn97bGLz0ib0Sfz1bjiaoOYA027iasqSG0sj  piby4vce3AtaPu6cIhBHkt6IjlkY9YnDsfw/0"]}
       * text_image_list : [{"image_url":"http://mmbiz.qpic.cn/mmbiz/p98FjXy8LacgHxp3sJ3vn97bGLz0ib0Sfz1bjiaoOYA027iasqSG0sjpiby4vce3AtaPu6cIhBHkt6IjlkY9YnDsfw/0","text":"此菜品精选食材，以独特的烹饪方法，最大程度地刺激食 客的味蕾"},{"image_url":"http://mmbiz.qpic.cn/mmbiz/p98FjXy8LacgHxp3sJ3vn97bGLz0ib0Sfz1bjiaoOYA027iasqSG0sj piby4vce3AtaPu6cIhBHkt6IjlkY9YnDsfw/0","text":"此菜品迎合大众口味，老少皆宜，营养均衡"}]
       * time_limit : [{"type":"MONDAY","begin_hour":0,"end_hour":10,"begin_minute":10,"end_minute":59},{"type":"HOLIDAY"}]
       * business_service : ["BIZ_SERVICE_FREE_WIFI","BIZ_SERVICE_WITH_PET","BIZ_SERVICE_FREE_PARK","BIZ_SERVICE_DELIVER"]
       */

      private UseConditionBean use_condition;
      @SerializedName("abstract")
      private AbstractBean abstractX;
      private List<TextImageListBean> text_image_list;
      private List<TimeLimitBean> time_limit;
      private List<String> business_service;

      public UseConditionBean getUse_condition() {
        return use_condition;
      }

      public void setUse_condition(UseConditionBean use_condition) {
        this.use_condition = use_condition;
      }

      public AbstractBean getAbstractX() {
        return abstractX;
      }

      public void setAbstractX(AbstractBean abstractX) {
        this.abstractX = abstractX;
      }

      public List<TextImageListBean> getText_image_list() {
        return text_image_list;
      }

      public void setText_image_list(List<TextImageListBean> text_image_list) {
        this.text_image_list = text_image_list;
      }

      public List<TimeLimitBean> getTime_limit() {
        return time_limit;
      }

      public void setTime_limit(List<TimeLimitBean> time_limit) {
        this.time_limit = time_limit;
      }

      public List<String> getBusiness_service() {
        return business_service;
      }

      public void setBusiness_service(List<String> business_service) {
        this.business_service = business_service;
      }

      public static class UseConditionBean {
        /**
         * accept_category : 鞋类
         * reject_category : 阿迪达斯
         * can_use_with_other_discount : true
         */

        private String accept_category;
        private String reject_category;
        private boolean can_use_with_other_discount;

        public String getAccept_category() {
          return accept_category;
        }

        public void setAccept_category(String accept_category) {
          this.accept_category = accept_category;
        }

        public String getReject_category() {
          return reject_category;
        }

        public void setReject_category(String reject_category) {
          this.reject_category = reject_category;
        }

        public boolean isCan_use_with_other_discount() {
          return can_use_with_other_discount;
        }

        public void setCan_use_with_other_discount(boolean can_use_with_other_discount) {
          this.can_use_with_other_discount = can_use_with_other_discount;
        }
      }

      public static class AbstractBean {
        /**
         * abstract : 微信餐厅推出多种新季菜品，期待您的光临
         * icon_url_list : ["http://mmbiz.qpic.cn/mmbiz/p98FjXy8LacgHxp3sJ3vn97bGLz0ib0Sfz1bjiaoOYA027iasqSG0sj  piby4vce3AtaPu6cIhBHkt6IjlkY9YnDsfw/0"]
         */

        @SerializedName("abstract")
        private String abstractX;
        private List<String> icon_url_list;

        public String getAbstractX() {
          return abstractX;
        }

        public void setAbstractX(String abstractX) {
          this.abstractX = abstractX;
        }

        public List<String> getIcon_url_list() {
          return icon_url_list;
        }

        public void setIcon_url_list(List<String> icon_url_list) {
          this.icon_url_list = icon_url_list;
        }
      }

      public static class TextImageListBean {
        /**
         * image_url : http://mmbiz.qpic.cn/mmbiz/p98FjXy8LacgHxp3sJ3vn97bGLz0ib0Sfz1bjiaoOYA027iasqSG0sjpiby4vce3AtaPu6cIhBHkt6IjlkY9YnDsfw/0
         * text : 此菜品精选食材，以独特的烹饪方法，最大程度地刺激食 客的味蕾
         */

        private String image_url;
        private String text;

        public String getImage_url() {
          return image_url;
        }

        public void setImage_url(String image_url) {
          this.image_url = image_url;
        }

        public String getText() {
          return text;
        }

        public void setText(String text) {
          this.text = text;
        }
      }

      public static class TimeLimitBean {
        /**
         * type : MONDAY
         * begin_hour : 0
         * end_hour : 10
         * begin_minute : 10
         * end_minute : 59
         */

        private String type;
        private int begin_hour;
        private int end_hour;
        private int begin_minute;
        private int end_minute;

        public String getType() {
          return type;
        }

        public void setType(String type) {
          this.type = type;
        }

        public int getBegin_hour() {
          return begin_hour;
        }

        public void setBegin_hour(int begin_hour) {
          this.begin_hour = begin_hour;
        }

        public int getEnd_hour() {
          return end_hour;
        }

        public void setEnd_hour(int end_hour) {
          this.end_hour = end_hour;
        }

        public int getBegin_minute() {
          return begin_minute;
        }

        public void setBegin_minute(int begin_minute) {
          this.begin_minute = begin_minute;
        }

        public int getEnd_minute() {
          return end_minute;
        }

        public void setEnd_minute(int end_minute) {
          this.end_minute = end_minute;
        }
      }
    }

    public static class CustomField1Bean {
      /**
       * name_type : FIELD_NAME_TYPE_LEVEL
       * url : http://www.qq.com
       */

      private String name_type;
      private String url;

      public String getName_type() {
        return name_type;
      }

      public void setName_type(String name_type) {
        this.name_type = name_type;
      }

      public String getUrl() {
        return url;
      }

      public void setUrl(String url) {
        this.url = url;
      }
    }

    public static class CustomCell1Bean {
      /**
       * name : 使用入口2
       * tips : 激活后显示
       * url : http://www.xxx.com
       */

      private String name;
      private String tips;
      private String url;

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }

      public String getTips() {
        return tips;
      }

      public void setTips(String tips) {
        this.tips = tips;
      }

      public String getUrl() {
        return url;
      }

      public void setUrl(String url) {
        this.url = url;
      }
    }

    public static class BonusRuleBean {
      /**
       * cost_money_unit : 100
       * increase_bonus : 1
       * max_increase_bonus : 200
       * init_increase_bonus : 10
       * cost_bonus_unit : 5
       * reduce_money : 100
       * least_money_to_use_bonus : 1000
       * max_reduce_bonus : 50
       */

      private int cost_money_unit;
      private int increase_bonus;
      private int max_increase_bonus;
      private int init_increase_bonus;
      private int cost_bonus_unit;
      private int reduce_money;
      private int least_money_to_use_bonus;
      private int max_reduce_bonus;

      public int getCost_money_unit() {
        return cost_money_unit;
      }

      public void setCost_money_unit(int cost_money_unit) {
        this.cost_money_unit = cost_money_unit;
      }

      public int getIncrease_bonus() {
        return increase_bonus;
      }

      public void setIncrease_bonus(int increase_bonus) {
        this.increase_bonus = increase_bonus;
      }

      public int getMax_increase_bonus() {
        return max_increase_bonus;
      }

      public void setMax_increase_bonus(int max_increase_bonus) {
        this.max_increase_bonus = max_increase_bonus;
      }

      public int getInit_increase_bonus() {
        return init_increase_bonus;
      }

      public void setInit_increase_bonus(int init_increase_bonus) {
        this.init_increase_bonus = init_increase_bonus;
      }

      public int getCost_bonus_unit() {
        return cost_bonus_unit;
      }

      public void setCost_bonus_unit(int cost_bonus_unit) {
        this.cost_bonus_unit = cost_bonus_unit;
      }

      public int getReduce_money() {
        return reduce_money;
      }

      public void setReduce_money(int reduce_money) {
        this.reduce_money = reduce_money;
      }

      public int getLeast_money_to_use_bonus() {
        return least_money_to_use_bonus;
      }

      public void setLeast_money_to_use_bonus(int least_money_to_use_bonus) {
        this.least_money_to_use_bonus = least_money_to_use_bonus;
      }

      public int getMax_reduce_bonus() {
        return max_reduce_bonus;
      }

      public void setMax_reduce_bonus(int max_reduce_bonus) {
        this.max_reduce_bonus = max_reduce_bonus;
      }
    }
  }
}
