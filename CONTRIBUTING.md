# Team workflow

How we work on this project. Follow this from start to finish on every change.

---

## One-time setup

Clone the repo and configure Git to rebase by default on `git pull`:

```bash
git clone <repo-url>
cd <repo-name>

git config pull.rebase true
git config rebase.autoStash true
```

This means `git pull` will cleanly rebase your local commits on top of the remote instead of creating merge commits.

---

## The workflow

### 1. Pick or create an issue

Every change starts as an issue. Either pick one or open a new one. Assign it to yourself so the rest of us know it's being worked on.

If the issue is new, give it a clear action-oriented title, a short description, and the right labels (`feature` / `bug` / `backend` / `frontend` / etc.).

### 2. Update `master` and create a branch

Always branch from the latest `master`:

```bash
git checkout master
git pull
git checkout -b 12-add-login-endpoint
```

Branch name format: `<issue-number>-<short-kebab-case-description>`

Examples:

```
12-add-login-endpoint
27-fix-order-total-calculation
34-kitchen-display-ui
```

### 3. Work and commit

Commit small, focused changes with imperative messages:

```bash
git add .
git commit -m "Add login endpoint"
```

Good messages: *"Add login endpoint"*, *"Fix off-by-one in order total"*, *"Refactor table routing"*.

Bad messages: *"stuff"*, *"fix"*, *"added login"*, *"WIP"*.

### 4. Keep your branch up to date

Before opening a PR and before merging rebase onto the latest `master`:

```bash
git fetch origin
git rebase origin/master
```

If there are conflicts, Git will pause allowing you to fix the conflicts. Either fix them using an IDE's dedicateed rebasing interface or manually.

To abort the rebase and return to where you started:

```bash
git rebase --abort
```

After a successful rebase on a branch you've already pushed, force-push with lease:

```bash
git push --force-with-lease
```

`--force-with-lease` refuses to overwrite the remote if someone else pushed new commits in the meantime - always use it instead of plain `--force`.

### 5. Open a pull request

On GitHub, open a PR from your branch against `master`. Assign yourself and request review from at least one teammate. Additionally CodeRabbit will automatically write a review. Include `Closes #<issue-number>` in the description to automatically close the issue after merging.

### 7. Merge

- **Squash and merge** (default) - collapses your branch's commits into one commit on `master`. Use this for almost everything.
- **Rebase and merge** - keeps each of your commits. Use this only if the history is already clean and each commit tells its own story.

### 8. Clean up locally

```bash
git checkout master
git pull
git branch -d 12-add-login-endpoint
```

Then pick the next issue.

---

## Why we keep history linear

`master` should read as a clean sequence of changes - each commit is one merged PR, in the order they were merged. No spaghetti.

That's why:
- We rebase instead of merging `master` into our branches.
- We squash (or rebase-merge) PRs instead of creating merge commits.
- We protect `master` with "Require linear history" and "Block force pushes".

If you ever see `Merge branch 'master' into …` in a commit message on your branch, you merged instead of rebased - undo it with a fresh rebase before opening the PR.

---

## Common situations

**I committed to `master` by accident.**

```bash
git branch 12-my-work            # save your commit on a new branch
git reset --hard origin/master   # reset master back to remote
git checkout 12-my-work          # continue on the branch
```

**I want to undo my last commit but keep the changes.**

```bash
git reset --soft HEAD~1
```

**I want to completely throw away my last commit.**

```bash
git reset --hard HEAD~1
```

**My rebase is a mess and I just want out.**

```bash
git rebase --abort
```

**I pushed and then realised the commit message is wrong.**

```bash
git commit --amend -m "New correct message"
git push --force-with-lease
```

Only do this on your own branch. Never `--amend` on `master`.
