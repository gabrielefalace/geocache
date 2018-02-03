package org.falace.geocache

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.geo.Circle
import org.springframework.data.geo.Point
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class PlaceService {

  @Autowired
  private lateinit var template: RedisTemplate<Any?, Any?>

  fun get(key: String): Mono<Place> {
    val place = template.opsForValue().get(key) as Place
    return Mono.just(place)
  }

  // x,y are lng,lat
  fun put(key: String, place: Place): Long {
    template.opsForValue().set(key, place)

    // just a random place to try...
    val memberMap: Map<Any?, Point> = mutableMapOf(place to Point(52.459861, 13.370086))
    return template.opsForGeo().add("Place", memberMap)
  }

  fun geoGet() = template.opsForGeo().radius("Place", Circle(Point(52.459861, 13.370086), 50.0))


}