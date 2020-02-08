def twoSum(nums, target):
    nums_copy = nums.copy()
    rtype = []
    for i in range(len(nums_copy)):
        if target - nums_copy[i] in nums and i != nums.index(target - nums_copy[i]):
            rtype.append(i)
            rtype.append(nums.index(target - nums_copy[i]))
            # nums.pop(i)
            # nums.pop(nums.index(target - nums_copy[i]))
            return rtype


if __name__ == "__main__":
    # nums = [-1, -2, -3, -4, -5]
    nums = [1, 3, 5, 6, 9]
    target = 9
    result = twoSum(nums, target)
    print(result)
