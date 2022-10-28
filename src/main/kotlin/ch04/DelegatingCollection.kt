package ch04

class DelegatingCollection<T>(innerlist: Collection<T> = ArrayList<T>()) : Collection<T> by innerlist {

}