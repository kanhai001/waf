package com.chinaredstar.waf.request;

import com.chinaredstar.waf.Constant;
import com.chinaredstar.waf.util.ConfUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;

/**
 * @author:杨果
 * @date:2017/4/11 下午2:06
 *
 * Description:
 *
 * IP白名单拦截
 */
public class WIpHttpRequestFilter extends HttpRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(WIpHttpRequestFilter.class);

    @Override
    public boolean isBlacklist() {
        return false;
    }

    @Override
    public boolean doFilter(HttpRequest httpRequest, ChannelHandlerContext channelHandlerContext) {
        logger.debug("filter:{}", this.getClass().getName());
        for (Pattern pat : ConfUtil.getPattern(FilterType.WIP.name())) {
            Matcher matcher = pat.matcher(Constant.getRealIp(httpRequest, channelHandlerContext));
            if (matcher.find()) {
                return true;
            }
        }
        return false;
    }
}