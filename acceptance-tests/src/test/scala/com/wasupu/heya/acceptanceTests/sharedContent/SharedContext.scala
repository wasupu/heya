package com.wasupu.heya.acceptanceTests.sharedContent

object SharedContext extends CleanupSharedContextHook {

  def save(key: String, value: Any) {
    map = map + ((key, value))
  }

  def load(key: String): Any = map.get(key).get

  def contains(key: String): Boolean = map.contains(key)

  def cleanup = {
    map = map.empty
  }

  override def toString: String = map.toString

  var map = Map.empty[String, Any]
}
