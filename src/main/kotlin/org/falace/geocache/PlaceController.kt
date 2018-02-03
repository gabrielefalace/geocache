package org.falace.geocache

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
class PlaceController {


  @Autowired
  lateinit var placeService: PlaceService


  @GetMapping("place/{key}")
  fun get(@PathVariable key: String): Mono<Place> {
    return placeService.get(key)
  }

  @PostMapping("place/{key}")
  fun post(@PathVariable key: String) {
    placeService.put(key, Place("place::$key"))
  }


}