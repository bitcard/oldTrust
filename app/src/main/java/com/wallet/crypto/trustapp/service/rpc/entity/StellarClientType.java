package com.wallet.crypto.trustapp.service.rpc.entity;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
/* compiled from: RpcClientInjection.kt */
public @interface StellarClientType {
    StellarClientEnum value() default StellarClientEnum.STELLAR;
}
