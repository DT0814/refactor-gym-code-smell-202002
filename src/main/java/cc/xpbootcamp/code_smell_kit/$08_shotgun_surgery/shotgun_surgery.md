
```
class RequestEntity(val classId:Int,val requestId:String,val date:Date)
```

```
override fun story(class: Class, date: LocalDate): Request {
    val requestId = "${class.id}:date"
    repository.save( RequestEntity(class.id,
            requestId,
            date.year+date.month+class.id
            ))
}
```

```
override fun findRequestByDate(class: Class, date: LocalDate): Request {
   service.findByDate(date.year+date.month+class.id)
}
```

