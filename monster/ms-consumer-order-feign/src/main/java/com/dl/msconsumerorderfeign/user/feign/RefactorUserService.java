package com.dl.msconsumerorderfeign.user.feign;

import com.dl.msprovideruserapi.service.UserService;
import org.springframework.cloud.netflix.feign.FeignClient;


@FeignClient(name = "microservice-provider-user")
public interface RefactorUserService extends UserService {

}