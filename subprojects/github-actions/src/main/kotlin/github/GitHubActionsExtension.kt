package com.nisecoder.gradle.plugin.github

import com.nisecoder.gradle.plugin.github.env.GithubActionsEnv
import com.nisecoder.gradle.plugin.github.env.GithubActionsEnv.GITHUB_ACTION
import com.nisecoder.gradle.plugin.github.env.GithubActionsEnv.GITHUB_ACTIONS
import com.nisecoder.gradle.plugin.github.env.GithubActionsEnv.GITHUB_ACTION_PATH
import com.nisecoder.gradle.plugin.github.env.GithubActionsEnv.GITHUB_ACTOR
import com.nisecoder.gradle.plugin.github.env.GithubActionsEnv.GITHUB_API_URL
import com.nisecoder.gradle.plugin.github.env.GithubActionsEnv.GITHUB_BASE_REF
import com.nisecoder.gradle.plugin.github.env.GithubActionsEnv.GITHUB_EVENT_NAME
import com.nisecoder.gradle.plugin.github.env.GithubActionsEnv.GITHUB_EVENT_PATH
import com.nisecoder.gradle.plugin.github.env.GithubActionsEnv.GITHUB_GRAPHQL_URL
import com.nisecoder.gradle.plugin.github.env.GithubActionsEnv.GITHUB_HEAD_REF
import com.nisecoder.gradle.plugin.github.env.GithubActionsEnv.GITHUB_JOB
import com.nisecoder.gradle.plugin.github.env.GithubActionsEnv.GITHUB_REF
import com.nisecoder.gradle.plugin.github.env.GithubActionsEnv.GITHUB_REPOSITORY
import com.nisecoder.gradle.plugin.github.env.GithubActionsEnv.GITHUB_RUN_ID
import com.nisecoder.gradle.plugin.github.env.GithubActionsEnv.GITHUB_RUN_NUMBER
import com.nisecoder.gradle.plugin.github.env.GithubActionsEnv.GITHUB_SERVER_URL
import com.nisecoder.gradle.plugin.github.env.GithubActionsEnv.GITHUB_SHA
import com.nisecoder.gradle.plugin.github.env.GithubActionsEnv.GITHUB_WORKFLOW
import com.nisecoder.gradle.plugin.github.env.GithubActionsEnv.GITHUB_WORKSPACE
import com.nisecoder.gradle.plugin.github.env.GithubActionsEnv.RUNNER_NAME
import com.nisecoder.gradle.plugin.github.env.GithubActionsEnv.RUNNER_OS
import com.nisecoder.gradle.plugin.github.env.GithubActionsEnv.RUNNER_TEMP
import com.nisecoder.gradle.plugin.github.env.GithubActionsEnv.RUNNER_TOOL_CACHE
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.api.provider.ProviderFactory
import org.gradle.kotlin.dsl.property

/**
 *  [Environment variables](https://docs.github.com/en/actions/learn-github-actions/environment-variables)
 */
@Suppress("unused")
abstract class GitHubActionsExtension(
    private val objects: ObjectFactory,
    private val providers: ProviderFactory
) {
    /**
     * The name of the workflow.
     */
    val workflow: Property<String> = env(GITHUB_WORKFLOW)

    /**
     * A unique number for each run within a repository. This number does not change if you re-run the workflow run.
     */
    val runId: Property<String> = env(GITHUB_RUN_ID)

    /**
     * A unique number for each run of a particular workflow in a repository. This number begins at 1 for the workflow's first run, and increments with each new run. This number does not change if you re-run the workflow run.
     */
    val runNumber: Property<String> = env(GITHUB_RUN_NUMBER)

    /**
     * The job_id of the current job.
     */
    val job: Property<String> = env(GITHUB_JOB)

    /**
     * The unique identifier (id) of the action.
     */
    val action: Property<String> = env(GITHUB_ACTION)

    /**
     * The path where your action is located. You can use this path to access files located in the same repository as your action. This variable is only supported in composite actions.
     */
    val actionPath: Property<String> = env(GITHUB_ACTION_PATH)

    /**
     * Always set to true when GitHub Actions is running the workflow. You can use this variable to differentiate when tests are being run locally or by GitHub Actions.
     */
    val actions: Property<String> = env(GITHUB_ACTIONS)

    /**
     * The name of the person or app that initiated the workflow. For example, octocat.
     */
    val actor: Property<String> = env(GITHUB_ACTOR)

    /**
     * The owner and repository name. For example, `octocat/Hello-World.`
     */
    val repository: Property<String> = env(GITHUB_REPOSITORY)

    /**
     * The name of the webhook event that triggered the workflow.
     */
    val eventName: Property<String> = env(GITHUB_EVENT_NAME)

    /**
     * The path of the file with the complete webhook event payload. For example, /github/workflow/event.json.
     */
    val eventPath: Property<String> = env(GITHUB_EVENT_PATH)

    /**
     * The GitHub workspace directory path, initially empty. For example, /home/runner/work/my-repo-name/my-repo-name. The actions/checkout action will check out files, by default a copy of your repository, within this directory.
     */
    val workspace: Property<String> = env(GITHUB_WORKSPACE)

    /**
     * 	The commit SHA that triggered the workflow. For example, `ffac537e6cbbf934b08745a378932722df287a53`
     */
    val sha: Property<String> = env(GITHUB_SHA)

    /**
     * The branch or tag ref that triggered the workflow. For example, refs/heads/feature-branch-1. If neither a branch or tag is available for the event type, the variable will not exist.
     */
    val ref: Property<String> = env(GITHUB_REF)

    /**
     * Only set for pull request events. The name of the head branch.
     */
    val headRef: Property<String> = env(GITHUB_HEAD_REF)

    /**
     * Only set for pull request events. The name of the base branch.
     */
    val baseRef: Property<String> = env(GITHUB_BASE_REF)

    /**
     * Returns the URL of the GitHub server. For example: https://github.com.
     */
    val serverUrl: Property<String> = env(GITHUB_SERVER_URL)

    /**
     * Returns the API URL. For example: https://api.github.com.
     */
    val apiUrl: Property<String> = env(GITHUB_API_URL)

    /**
     * Returns the GraphQL API URL. For example: https://api.github.com/graphql.
     */
    val graphQLUrl: Property<String> = env(GITHUB_GRAPHQL_URL)

    /**
     * The name of the runner executing the job.
     */
    val runnerName: Property<String> = env(RUNNER_NAME)

    /**
     * The operating system of the runner executing the job. Possible values are Linux, Windows, or macOS.
     */
    val runnerOs: Property<String> = env(RUNNER_OS)

    /**
     * The path to a temporary directory on the runner. This directory is emptied at the beginning and end of each job. Note that files will not be removed if the runner's user account does not have permission to delete them.
     */
    val runnerTemp: Property<String> = env(RUNNER_TEMP)

    /**
     * The path to the directory containing preinstalled tools for GitHub-hosted runners. For more information, see "Specifications for GitHub-hosted runners".
     */
    val runnerToolCache: Property<String> = env(RUNNER_TOOL_CACHE)

    private fun env(env: GithubActionsEnv): Property<String> {
        return objects.property<String>().convention(providers.provider { System.getenv(env.name) })
    }
}
