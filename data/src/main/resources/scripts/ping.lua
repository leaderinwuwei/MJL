local value = "a" .. 'b'


local foo = redis.call('ping')
redis.call('set','ping',value)
return foo