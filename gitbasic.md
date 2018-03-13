-Create a new git:
`git init` create a .git file(invisible) to initialize git in current file(directory)
`git status` view status of folder (new file, del file, edit file....)
(before commit, should probably tell git who we are):
`git config --global user.name <yourname>`
`git config --global user.email <your@email>`

- Commit
`git commit` commit a change/snapshot to history
`git commit -m "ur text to comment ur commit"`

- Log
`git log` check/view the history of commit, it details
`git log --oneline` just view a comment (not details)
`git log --oneline filename` see any commit had change  this filename

- How to use git
(git init) -> git status -> git add .(if had change) -> git status(done) -> git commit -m "" -> git config(if login yet) -> git log

-Undoing changes git
`git checkout <checksumcode>` undo git to a times of branch with checksumcode (7 char) -> get checksumcode by : `git log --oneline`

- Tag
`git tag -a version -m "something u want to write"` tags the most recent commit with a version number. use `git checkout version` to check to this tag

`git revert checksumcode` revert branch to checksumcode
`git reset --hard` reset any change before commit
`git clean -f` remove all untracked file
`git branch` list all branch
`git branch branchname` create a new branch with name branchname as current
`git checkout branchname` take HEAD to this branchname
`git merge branchname` merge branchname to checkout branch
`git branch -d branchname` delete a branch
`git rm filename` remove a file from working directory
`git branch -D branchname` force the removal of an unmerged branch
`git clone /directory/dir` clone a repository
`git push origin master` push commit to branch master
`git pull` update new source