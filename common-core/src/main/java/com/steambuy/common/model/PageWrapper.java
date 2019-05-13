package com.steambuy.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PageWrapper<T> extends Wrapper<T> {

    private static final long serialVersionUID = 666985064788933395L;

    private PageInfo pageInfo;


    /**
     * Instantiates a new Page wrapper.
     */
    PageWrapper() {

    }


    /**
     * Instantiates a new Page wrapper.
     *
     * @param code    the code
     * @param message the message
     */
    public PageWrapper(int code, String message) {
        super(code, message);
    }

    /**
     * Instantiates a new pageWrapper default code=200
     *
     * @param result   the result
     * @param pageInfo the page util
     */
    public PageWrapper(T result, PageInfo pageInfo) {
        super();
        this.setResult(result);
        this.setPageInfo(pageInfo);
    }

    /**
     * Instantiates a new Page wrapper.
     *
     * @param code     the code
     * @param message  the message
     * @param result   the result
     * @param pageInfo the page util
     */
    PageWrapper(int code, String message, T result, PageInfo pageInfo) {
        super(code, message, result);
        this.pageInfo = pageInfo;
    }

    /**
     * Sets the 分页数据 , 返回自身的引用.
     *
     * @param pageInfo the page util
     *
     * @return the page wrapper
     */
    public PageWrapper<T> pageInfo(PageInfo pageInfo) {
        this.setPageInfo(pageInfo);
        return this;
    }

    /**
     * Sets the 结果数据 , 返回自身的引用.
     *
     * @param result the result
     *
     * @return the page wrapper
     */
    @Override
    public PageWrapper<T> result(T result) {
        super.setResult(result);
        return this;
    }
}
