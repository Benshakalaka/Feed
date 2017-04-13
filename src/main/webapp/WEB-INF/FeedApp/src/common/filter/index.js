export const twoNumberFilter = value => {
  return (value < 10 ? ('0' + value) : String(value))
}
