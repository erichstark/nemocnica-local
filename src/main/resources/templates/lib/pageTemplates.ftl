<#-- @ftlvariable name="user" type="sk.stuba.fei.team.local.security.CustomUser" -->
<#-- @ftlvariable name="pageTitle" type="java.lang.String" -->
<#-- @ftlvariable name="menu" type="java.util.List<sk.stuba.fei.nemocnica.menu.MenuItem>" -->
<#import "/spring.ftl" as spring />
<#macro genericPage>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="<@spring.url '/fav/fav.ico'/>"/>
    <link type="text/css" href="<@spring.url '/css/bootstrap.min.css'/>" rel="stylesheet"/>
    <link type="text/css" href="<@spring.url '/css/custom.css'/>" rel="stylesheet"/>
    <title>${pageTitle}</title>
</head>
<body>
    <#nested>
<script src="<@spring.url '/js/jquery-2.1.4.min.js'/>"></script>
<script src="<@spring.url '/js/bootstrap.min.js'/>"></script>
</body>
</html>
</#macro>

<#macro menuFooterPage>
    <@genericPage>
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="<@spring.url '/'/>"><@spring.message "ProjectName"/></a>
            </div>
            <div class="navbar-header pull-right">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i
                                class="glyphicon glyphicon-user"></i>
                            <#if user??>${user.getUsername()}</#if>
                            <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="#"><i class="glyphicon glyphicon-user"></i> Profile</a>
                            </li>
                            <li>
                                <a href="#"><i class="glyphicon glyphicon-envelope"></i> Inbox</a>
                            </li>
                            <li>
                                <a href="#"><i class="glyphicon glyphicon-cog"></i> Settings</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="<@spring.url '/logout'/>"><i
                                        class="glyphicon glyphicon-log-out"></i> <@spring.message "SignOut" /></a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <#if user.stringAuthorities?seq_contains("ADMIN")>
                        <li><a href="<@spring.url '/admin'/>">Admin</a></li>
                    </#if>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container">
        <#nested>
    </div>
    <footer class="footer">
        <div class="container">
            <p class="text-muted">Place sticky footer content here.</p>
        </div>
    </footer>
    </@genericPage>
</#macro>

<#macro dashboardPage>
    <@genericPage>
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                        aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<@spring.url '/'/>"><@spring.message "ProjectName"/></a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i
                                class="glyphicon glyphicon-user"></i>
                            <#if user??>${user.getUsername()}</#if>
                            <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="#"><i class="glyphicon glyphicon-user"></i> Profile</a>
                            </li>
                            <li>
                                <a href="#"><i class="glyphicon glyphicon-envelope"></i> Inbox</a>
                            </li>
                            <li>
                                <a href="#"><i class="glyphicon glyphicon-cog"></i> Settings</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="<@spring.url '/logout'/>"><i
                                        class="glyphicon glyphicon-log-out"></i> <@spring.message "SignOut" /></a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container-fluid">
        <div class="row">
            <div id="side-navbar" class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-sidebar">
                    <li><a href="<@spring.url '/admin'/>">Prehľad</a></li>
                    <li><a href="<@spring.url '/admin/facility'/>">Zariadenia</a></li>
                    <li><a href="<@spring.url '/admin/office'/>">Ambulancie</a></li>
                    <li><a href="<@spring.url '/admin/insurance'/>">Poistovne</a></li>
                    <li><a href="<@spring.url '/admin/specialization'/>">Špecializácie</a></li>
                </ul>
            </div>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <#nested>
            </div>
        </div>
    </div>
    </@genericPage>
</#macro>