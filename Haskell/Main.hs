-- 0 is removing even positions, 1 is removing odd positions, 2 is removing positions divisible by 3
rule 0 = \pos -> even pos
rule 1 = \pos -> odd pos
rule 2 = \pos -> pos `mod` 3 == 0

-- gives every number its position
withPositions xs = zip [1..] xs

-- keeps only the pairs whose position is not removed by the rule
keep r pairs = filter(\(pos, x) -> not (r pos)) pairs

-- takes the numbers back out of the pairs
onlyNumbers pairs = map(\(pos, x) -> x) pairs

removeBy r xs = onlyNumbers (keep r (withPositions xs))

-- recursion
play [x] step = x
play xs step = play (removeBy (rule step) xs) ((step + 1) `mod` 3)

main = do
  n <- readLn :: IO Int
  let numbers = [1..n]
  print (play numbers 0)