import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:8888'
axios.defaults.timeout = 5000
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'

const errorHandler = (error, reject) => {
  if (error.response) {
    reject(error.response.status < 500 ? error.response.data : '服务器错误')
  } else {
    reject('未知错误')
  }
}

const get = (url) => {
  return new Promise((resolve, reject) => {
    axios.get(url).then(response => {
      resolve(JSON.parse(response.data))
    }).catch(error => errorHandler(error, reject))
  })
}

const post = (url, params) => {
  return new Promise((resolve, reject) => {
    axios.post(url, params).then(response => {
      resolve(JSON.parse(response.data))
    }).catch(error => errorHandler(error, reject))
  })
}

export const login = ({email, passowrd}) => {
  return post(`/users/login/${email}/${passowrd}`, {})
}

export const regist = ({email, username, password}) => {
  return post('/users', {
    userEmail: email,
    userName: username,
    userPwd: password
  })
}
