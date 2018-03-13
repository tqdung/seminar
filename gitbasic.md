- Create a new git:
`git init`: create a .git file (invisible) to initialize git in current file (directory)
`git status`: view status of folder (new file, del file, edit file….)
`git add ./filename`: snapshot a change
(before commit, should probably tell Git who we are):
`git config --global user.name YourName`
`git config --global user.email your@email`

- Commit
`git commit`: commit a change/snapshot to history
`git commit -m “ur text to comment ur commit”`
`git log`: check/view the history of commit, it details
`git log --oneline`: just view a comment (not details)
`git log --oneline filename`: see any commit had change this filename

(git init) -> git status -> git add .(if had change) -> git status -> git commit -m “” -> git config -> git log 
Undoing changes git:
`git checkout <checksumcode>`: undo git to a times of branch with checksum code (7 char) -> get checksum code by: git log --oneline
`git tag -a version -m “something u want to write”`: tags the most recent commit with a version number. use git checkout version(v1.0) to check to this tag 
- use git checkout master to switch to branch ‘master’
`git revert checksumcode`: revert branch to checksumcode
`git reset --hard`: reset any change before commit 
`git clean -f`: remove all untracked file
`git branch`: list all branch
`git branch branchname`: create a new branch with name branchname as current 
`git checkout branchname`: make HEAD to this branchname
`git merge branchname`: merge branchname to checkedout branch
`git brand -d branchname`: delete a branch
`git rm filename`: remove a file from working directory 
`git brand -D brandname`: Force the removal of an unmerged branch
`git clonet /directory/dir`: clone a repository 
`git push origin master`: push commit to branch master
`git pull`: update new source