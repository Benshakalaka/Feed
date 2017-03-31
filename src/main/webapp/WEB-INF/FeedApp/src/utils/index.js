/**
 * Created by Ben on 2017/3/31.
 */
export const isSupportEmailInput = (function () {
  let input = document.createElement('input')
  input.type = 'email'
  return (input.type === 'email')
})()

export const emailFormatValidate = email => {
  return /^([a-zA-Z0-9]+[_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/.test(email)
}
