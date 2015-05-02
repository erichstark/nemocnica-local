<#import "lib/pageTemplates.ftl" as g>
<#import "/spring.ftl" as spring>
<#assign pageTitle in g><@spring.message "LogIn"/></#assign>
<#assign pageTitle><@spring.message "LogIn"/></#assign>
<@g.genericPage>
<div class="container">
    <div id="login-panel" class="panel panel-default">
        <div class="panel-heading"><h3 class="panel-title"><strong>${g.pageTitle}</strong></h3></div>
        <div class="panel-body">
            <div class="text-danger">
                <#if RequestParameters.error??>
                    Invalid username and password.
                </#if>
                <#if RequestParameters.logout??>
                    You have been logged out.
                </#if>
            </div>
            <form role="form" action="<@spring.url '/login'/>" method="POST">
                <div class="form-group">
                    <label for="username">Prihlasovacie meno</label>
                    <input type="text" class="form-control" id="username" name="username" required autofocus>
                </div>
                <div class="form-group">
                    <label for="password">Heslo</label>
                    <input type="password" class="form-control" id="password" name="password">
                </div>
                <button type="submit" class="btn btn-sm btn-default">Prihlásiť</button>
            </form>
        </div>
    </div>
</div>
</@g.genericPage>