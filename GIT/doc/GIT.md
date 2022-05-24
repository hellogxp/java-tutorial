## GIT Usage

### Bring a local folder to remote git repo

```git
 git init
 git add .
 git commit -m "First commit"
```

Sets the new remote

```git
git remote add origin <remote repository URL>
```

Verifies the new remote URL

```git
git remote -v
```

Push the changes in your local repository to GitHub if there is a remote branch called master (or main if that's what
you're using)

```git
git push origin master
```

Otherwise you will have to name local branch first by

```git
git branch -m <new_name>
```

and then push it to add a new branch called <new_name>

```git
git push origin -u <new_name>
```

### Reset current HEAD to the specific state

#### Go back to previous state (hard).

Do not keep the changes, the existing changes will be lost

```git
git reset --hard HEAD^
```

#### Go back to previous state (soft).

Keeping the changes, the existing changes will be kept, and in the uncommite, we can use `git status` or `git diff` to
view the changes.

```git
git reset --soft HEAD^
```

#### Go back to a specific commit

```git
git reset --soft HEAD~N
```

### Configure your Git username/email

#### To set your global username/email configuration:

1. Open the command line.
2. Set your username:

```git
git config --global user.name "FIRST_NAME LAST_NAME"
```

3. Set your email address:

#### To set repository-specific username/email configuration:

1. From the command line, change into the repository directory.
2. Set your username:

```git
git config user.name "FIRST_NAME LAST_NAME"
```

3. Set your email address:

```git
git config user.email "MY_NAME@example.com"

```

4. Verify your configuration by displaying your configuration file:

```git
cat .git/config
```

### Fetch remote brancds that not exis locally and set up to track remote branhcn

#### Solution one    

1. Fetch all of remote brands for you

```git
git fetch -all
```    

We can see the branches available for checkout with:

```shell
git branch -v -a
```

2. Set up to track the remote branch

```git
git switch origin/remote_branch
```     

In this case Git is guessing (can be disabled with --no-guess) that you are trying to checkout and track the remote
branch with the same name.

#### Solution two

1. We first look at local branches in our repository:
    ```
    $ git branch
    * master
    ```
But there are other branches hiding in our reposotory! We can see these using `-a` flag    
```
$ git branch -a
* master
  remotes/origin/HEAD
  remotes/origin/master
  remotes/origin/v1.0-stable
  remotes/origin/experimental
```

If we just wanto take a quick peek at an upstream branch, we can check it directly:    
```
$ git checkout origin/experimental
```
But if you want to work on that branch, you'll need to create a local tracking branch which is done automatically by:
```
$ git checkout experimental

```
and you will see:    
```
Branch experimental set up to track remote branch experimental from origin.
Switched to a new branch 'experimental'
```

Here, "new branch" simply means that the branch is taken from the index and created locally for you. As the previous 
line tells you, the branch is being set up to track the remote branch, which usually means the origin/branch_name branch.

Now, if you look at uyour local branches, you will see below:

```
$ git branch
* experimental
  master
```

### Based on the remote branch to build a local branch, and then set up to track remote branch

```git
git checkout --track origin/remotebranch
```

Based on the remote branch "origin/remotebranch", we now have a new local branch named "remotebranch".

### Show a branch log

If you are not in the branch, then you can add the branch name to the "git log" command, like this:

```git
git log master..branchname
```

If your branch was made off of origin/master, then say origin/master instead of master.

### Back to a previous commit and push to remote
Back to last commit
```shell
 git reset --hard HEAD^
```
or a certain commit id

```
 git reset --hard commitid
```

```
git push -f origin remotebranch
```

### Remove files or folders from git trace    
```shell
git rm --cached <file>
```    
```shell
git rm -r --cached <folder>
```
