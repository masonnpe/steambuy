package com.steambuy.model;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Review implements Serializable {

    @Id
    private String _id;

    /**
     * 订单id
     */
    private String orderid;
    /**
     * 商品id
     */
    private String spuid;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论时间
     */
    private Date publishtime;
    /**
     * 评论用户id
     */
    private String userid;
    /**
     * 评论用户昵称
     */
    private String nickname;
    /**
     * 评论的浏览量
     */
    private Integer visits;
    /**
     * 评论的点赞数
     */
    private Integer thumbup;
    /**
     * 评论中的图片
     */
    private List<String> images;
    /**
     * 评论的回复数
     */
    private Integer comment;
    /**
     * 该评论是否可以被回复
     */
    private Boolean iscomment;
    /**
     * 该评论的上一级id
     */
    private String parentid;
    /**
     * 是否是顶级评论
     */
    private Boolean isparent;
    /**
     * 评论的类型
     */
    private Integer type;

    /**
     * json转换需要
     */
    public Review() {
    }
}
