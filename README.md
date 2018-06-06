# A TCP Queue server

It supports following commands

# use

This is a producer command to create/attach to a tube.
syntax:

use\r\n

(tube_name)

Response:
USING (tube_name)\r\n


# put

This is a producer command to add jobs to the current tube.
syntax:

put\r\n

(job body text)

Response:
INSERTED (job id)\r\n


# watch

This is a consumer command to add tube to it's watch list.
syntax:

watch\r\n

<tube_name>

Response:
WATCHING (no of tubes in the watch list)\r\n


# reserve

This is a consumer command to consume jobs from the current watch list tubes.
syntax:

reserve\r\n

Response:
RESERVED (job id)  (job size)\r\n

(job body)\r\n


# delete

This is a producer command to delete a job from the current tube.
syntax:

delete\r\n

(job id)

Response:
DELETED (job id)\r\n      if success

else

NOT_FOUND (job id)\r\n