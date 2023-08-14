package com.knife.config;

import com.knife.core.AccessMeta;
import lombok.Data;

/**
 * @Description: TODO
 * @Author geey
 * @Date 2023/8/3 13:55
 * @Version 1.0
 */
@Data
public class Access extends AccessMeta {
    /**
     * 身份标志
     */
    private LoginUser user;
    private String requestId;
    private String ip;
}
