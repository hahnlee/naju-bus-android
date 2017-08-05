package io.sn0wle0pard.najubus.model

import io.realm.RealmObject

public open class StaredStation: RealmObject() {
    public open var lineID: Int = -1
    public open var lineName: String = ""
    public open var lineDir: String = ""
}
