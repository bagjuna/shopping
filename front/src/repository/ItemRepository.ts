import {inject, singleton} from "tsyringe";
import HttpRepository from "@/repository/HttpRepository.ts";
import type ItemWrite from "@/entity/item/ItemWrite.ts";
import Item from "@/entity/item/Item.ts";


@singleton()
export default class ItemRepository {
  constructor(@inject(HttpRepository) private readonly httpRepository: HttpRepository) {
  }

  public write(request: ItemWrite) {
    return this.httpRepository.post({
      path: '/api/save/item',
      body: request,
    })
  }

  public get(id: number) {
    console.log(id)
    return this.httpRepository.get<Item>({path: `/api/get/${id}`}, Item)
  }

  public getList(page: number) {
    return this.httpRepository.getList<Item>(
      {
        path: `/api/getList?page=${page}&size=10`,
      },
      Item
    )
  }

  public delete(id: number) {
    return this.httpRepository.delete({
      path: `/api/delete/${id}`,
    })
  }
}
