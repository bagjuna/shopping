import { DateTimeFormatter, LocalDateTime } from '@js-joda/core'
import { Transform } from 'class-transformer'

export default class Item {
  public id = 0
  public name = ''
  public description = ''
  public price = 0
  public quantity = 0

  @Transform(({ value }) => LocalDateTime.parse(value, DateTimeFormatter.ISO_LOCAL_DATE_TIME))
  public createdAt = LocalDateTime.now()
  public memberId = 0

  public getDisplayRegDate() {
    return this.createdAt.format(DateTimeFormatter.ofPattern('yyyy년 MM월 dd일 HH시 mm분'))
  }

  public getDisplaySimpleRegDate() {
    return this.createdAt.format(DateTimeFormatter.ofPattern('yyyy-MM-dd'))
  }
}
