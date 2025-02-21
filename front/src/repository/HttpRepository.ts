import AxiosHttpClient, { type HttpRequestConfig } from '@/http/AxiosHttpClient.ts';
import { inject, singleton } from 'tsyringe';
import { plainToInstance } from 'class-transformer';
import Null from '@/entity/data/Null.ts';
import Paging from '@/entity/data/Paging.ts';

@singleton()
export default class HttpRepository {
  constructor(@inject(AxiosHttpClient) private readonly httpClient: AxiosHttpClient) {}

  public get<T>(config: HttpRequestConfig, clazz: { new(...args: any[]): T }): Promise<T> {
    return this.httpClient.request({ ...config, method: 'GET' })
      .then((response) => plainToInstance(clazz, response));
  }

  public getList<T>(config: HttpRequestConfig, clazz: { new(...args: any[]): T }): Promise<Paging<T>> {
    return this.httpClient.request({ ...config, method: 'GET' }).then((response) => {
      const paging = plainToInstance<Paging<T>, any>(Paging, response);
      // const items = plainToInstance(clazz, response.items) as T[];
      const items = plainToInstance(clazz, response.content) as T[];
      paging.setItems(items);
      return paging;
    });
  }

  // Overloads for post:
  public post<T>(config: HttpRequestConfig, clazz: { new(...args: any[]): T }): Promise<T>;
  public post(config: HttpRequestConfig, clazz?: null): Promise<Null>;
  public post<T>(config: HttpRequestConfig, clazz: { new(...args: any[]): T } | null = null): Promise<T | Null> {
    return this.httpClient.request({ ...config, method: 'POST' }).then((response) => {
      if (clazz !== null) {
        return plainToInstance(clazz, response);
      } else {
        return plainToInstance(Null, response);
      }
    });
  }

  // Overloads for patch:
  public patch<T>(config: HttpRequestConfig, clazz: { new(...args: any[]): T }): Promise<T>;
  public patch(config: HttpRequestConfig, clazz?: null): Promise<Null>;
  public patch<T>(config: HttpRequestConfig, clazz: { new(...args: any[]): T } | null = null): Promise<T | Null> {
    return this.httpClient.request({ ...config, method: 'PATCH' }).then((response) => {
      if (clazz !== null) {
        return plainToInstance(clazz, response);
      } else {
        return plainToInstance(Null, response);
      }
    });
  }

  // Overloads for delete:
  public delete<T>(config: HttpRequestConfig, clazz: { new(...args: any[]): T }): Promise<T>;
  public delete(config: HttpRequestConfig, clazz?: null): Promise<Null>;
  public delete<T>(config: HttpRequestConfig, clazz: { new(...args: any[]): T } | null = null): Promise<T | Null> {
    return this.httpClient.request({ ...config, method: 'DELETE' }).then((response) => {
      if (clazz !== null) {
        return plainToInstance(clazz, response);
      } else {
        return plainToInstance(Null, response);
      }
    });
  }
}
