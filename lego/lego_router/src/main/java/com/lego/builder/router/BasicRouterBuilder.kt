import com.ktnail.x.uri.Uri
import com.ktnail.x.uri.toUri
import com.lego.builder.query.QueriesBuildable
import com.lego.route.ResultGroups
import com.lego.route.Results
import com.lego.route.exception.BadUriException
import com.lego.router.Router

abstract class BasicRouterBuilder : RouterBuildable {
    protected abstract val queriesBuilder: QueriesBuildable
    protected abstract fun createUri(): String

    private val resultGroups: ResultGroups = ResultGroups()
    private var checkRouterVersion: Int? = null

    private fun buildUri(): Uri {
        val uri = createUri()
        return uri.toUri() ?: throw BadUriException(uri)
    }

    override fun checkRouterVersion(version: Int) = apply {
        checkRouterVersion = version
    }

    override fun receiveResults(receive: (Results) -> Unit) = apply {
        resultGroups.load(Results(receive))
    }

    override fun build() = Router(buildUri(), queriesBuilder.buildQueries(), resultGroups, checkRouterVersion)

}