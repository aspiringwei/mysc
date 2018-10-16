git 使用需要掌握的
---

1. 分布式、集中式概念

2. 基本配置(全局用户信息：用户名、邮箱)、命令
	- git --help 查看命令帮助；git help 具体命令。 掌握基本命令如 init/add/commit/log/pull/push
	- git config --global user.name linwb
	- git config --global user.email no.lwb@163.com

3. 了解常见基本 git flow 分支
	- 产品分支 master
	- 开发分支 develop
	- 功能分支 feature
	- 发布版本分支 release
	- 修复补丁分支 hotfix

5. 基本概念
	- 工作区：即任意目录
	- 版本库：带有 .git 隐藏目录
		- index：stage 暂存区。git add file.. 添加到此处
		- HEAD：git init 时会自动创建一个 master 分支,HEAD指向该分支。git commit 添加到HEAD指向的版本库

4. 修改撤销、恢复
	- 未 git add,用命令 git checkout -- file-name 丢弃工作区的修改
	- 已经 git add,用命令 git reset HEAD file-name可以把暂存区的修改撤销掉（unstage），重新放回工作区。
	- 已经 commit,版本回退
		- 每次提交都有一个commit-id，git会串成一条时间线，形成图谱。HEAD指向当前版本，HEAD^指向上一个版本
		 使用命令 git reset commit-id 就可以进行版本回退 其中 commit-id 不必写完整
		- 查看引用日志 git reflog 可以任意 reset 过去未来

6. 远程仓库 默认关联名称是 origin，可自定义 
	- clone with SSH(使用ssh秘钥和账户密码,需要创建 SSH Key 秘钥对并添加公钥到账户列表)、HTTPS
	- 把本地仓库与远程仓库关联 git remote add origin git-url 多关联：git remote add gitlab git-url0、git remote add github git-url1
	- 删除关联的远程库 git remote rm origin
	- 推送最新修改 git push [-u] origin master
	- 当存在 多份 ssh key 秘钥对，不同远程库的情况，git clone 不下来，需要执行下列操作
        ```shell
        $ cd ~/.ssh
        $ eval `ssh-agent -s`
        $ ssh-add key -- ssh-add命令是把专用密钥添加到ssh-agent的高速缓存中 -d 删除
        ```
	- git pull remote-name branch-name 拉取 如果失败，根据提示，本地分支与远程分支建立链接 track
        ```
        There is no tracking information for the current branch.
        Please specify which branch you want to merge with.
        See git-pull(1) for details.
            git pull remote-name branch-name
        If you wish to set tracking information for this branch you can do so with:
            git branch --set-upstream-to=origin/branch-name develop
        ```
	- git push remote-name branch-name 推送 如果失败，git pull 试图合并，冲突处理

7. git flow 使用

	- 新建功能分支，基于 develop 创建的 如 feature/f1
		- 完成功能开发，将 feature/ 合并到 develop 分支中，存在选项是否在开发分支上变基，完成后是否删除分支(默认是)，切换到 develop 分支
		
	- 建立新的发布版本 基于 develop 创建的 如 release/r1 
		- 完成发布版本 将 release/r1 合并到 develop 和 master,添加标签，完成后是否删除分支(默认是)，
		是否推送到远程仓库, 打 tag 'r1'，该切换到 develop 分支
	- 建立新的修复补丁 基于 master 创建的 如 hotfix/h1
		- 完成修复补丁，将 hotfix/h1 合并到 develop 和 master,添加标签，完成后是否删除分支(默认是)，
		是否推送到远程仓库, 打 tag 'h1'，该切换到 develop 分支
	- git tag 查看标签 版本库的一个快照
		- 默认标签是打在最新提交的commit上的 可通过历史提交的 commit-id 
		- 打标签：git tag [-a] tag-name [-m 'msg'] commit-id
		- 查看标签信息：git show tag-name
		- 删除标签：
			- 本地删除 git tag -d tag-name 
			- 远程删除 git push origin :refs/tags/tag-name
		- 推送标签到远程库：
			- 指定 tag-name 推送：git push origin tag-name
			- 全部推送：git push origin --tags

8. Merge conflict 合并冲突
    - 同时修改一个文件的相同行了，特别是多人在同一分支协作开发时候，更容易出现
	- git status 查看状态 冲突文件 Unmerged paths
	- cat file-name git 使用 <<< === >>> 标记出不同分支的内容，根据实际情况修改文件内容
		```
		<<<<<<< HEAD
		我什么也不知道
		=======
		什么都可以放弃嘛
		>>>>>>> feature/ct
		```
	- 再提交，合并完成，再push到远程仓库
	- git pull 冲突处理方式同上

9. 常用命令
	- 查看分支的合并图： git log --graph --pretty=oneline --abbrev-commit
	- 工作空间贮藏： git stash - Stash the changes in a dirty working directory away 把工作目录的修改隐藏起来，常用于突然在开发过程中需要修复 bug 的情况，需要把工作现场保护起来在修复bug然后再切回来
		- git stash list 列出储藏
		- git stash pop 恢复并删除隐藏
		- git stash apply 恢复不删除隐藏内容
	- 分支删除：git branch -d(-D强制删除) <分支>
	- 查看远程仓库信息：git remote -v
	- 在本地创建与远程仓库对应的分支：git checkout -b branch-name origin/branch-name
	- 变基：rebase 对未push的commit做操作
		- 把Git很乱的提交历史变成一条干净的直线 rebase操作可以把本地未push的分叉提交历史整理成直线，查看历史提交变化更容易

10. 其他
	- fork project pull request
	- 配置忽略特殊文件 [.gitignore](https://github.com/github/gitignore)
	- 别名配置 复杂命令简化
	```
	$ git config --global alias.st status
	$ git config --global alias.co checkout
	$ git config --global alias.ci commit
	$ git config --global alias.br branch
	$ git config --global alias.lg "log --color --graph --pretty=format:'%Cred%h %Creset -%C(yellow)%d%Creset %s %Cgreen(%cr) %C(bold blue)<%an>%Creset' --abbrev-commit"
	$ git config --global alias.lgg "log --color --graph --pretty=oneline  --abbrev-commit"
	```


栗子
---
1. 切换到开发分支：git checkout develop
2. 创建新的功能分支：git checkout -b feature/new1 啪啦啦，进行新功能研发，提交完毕
3. 功能研发完变基合并到 develop 删除功能分支：git checkout develop && git merge feature/new1 && git branch -d feature/new1
默认使用Fast-forward快进模式，将指定分支的提交指向当前分支 可以使用 --no-ff禁止快进模式，这样merge就需要生成一次commit


相关文档
---
1. https://git-scm.com/book/zh/v2
2. http://gitbook.liuhui998.com/
3. https://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000


版本号相关::
Release 表示 是正式的版本.
RC stands for Release Candidate 表示 后选版本
M stands for milestone 表示里程碑版本.
一般而言, 稳定性由上而下, 依次降低.