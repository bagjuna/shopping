import type { AxiosError } from 'axios'

export default class HttpError {
  private readonly code: string
  private readonly message: string

  // constructor(e: AxiosError) {
  //   this.code = e.response?.data.code ?? '500'
  //   this.message = e.response?.data.message ?? '네트워크 상태가 안좋아잉..'
  // }

  constructor(e: AxiosError) {
    const data = e.response?.data as { code: string; message: string }
    this.code = data?.code ?? '500'
    this.message = data?.message ?? '네트워크 상태가 안좋아잉..'
  }

  public getMessage() {
    return this.message
  }
}
