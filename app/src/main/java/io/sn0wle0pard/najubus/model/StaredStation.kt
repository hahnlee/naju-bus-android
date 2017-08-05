package io.sn0wle0pard.najubus.model

import io.realm.RealmObject

open class StaredStation: RealmObject() {
    open var lineID: Int = -1
    open var lineName: String = ""
    open var lineDir: String = ""
    open var latitude: Double = 0.0
    open var longitude: Double = 0.0
}
