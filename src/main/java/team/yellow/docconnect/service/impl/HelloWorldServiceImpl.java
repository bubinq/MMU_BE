package team.yellow.docconnect.service.impl;

import org.springframework.stereotype.Service;
import team.yellow.docconnect.payload.dto.HelloWorldDto;
import team.yellow.docconnect.service.HelloWorldService;

@Service
public class HelloWorldServiceImpl implements HelloWorldService {


    @Override
    public String helloWorld(HelloWorldDto helloWorldDto) {
        return helloWorldDto.getHelloWorld();
    }
}
