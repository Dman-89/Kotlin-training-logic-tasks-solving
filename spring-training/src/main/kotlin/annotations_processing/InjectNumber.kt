package annotations_processing

@Retention(AnnotationRetention.RUNTIME)
//@Target(AnnotationTarget.FIELD)
annotation class InjectNumber(val num: Int) {
}
