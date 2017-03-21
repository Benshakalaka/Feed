import MockData from './data.js'

export const getTypes = callback => {
	callback && callback(MockData)
}