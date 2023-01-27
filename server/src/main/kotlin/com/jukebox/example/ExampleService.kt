package com.jukebox.example

import org.springframework.stereotype.Service

@Service
class ExampleService {
    fun getSomeValue() = ExampleResponse("Hello World!", true)
}
