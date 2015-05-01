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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="<@spring.url '/js/bootstrap.min.js'/>"></script>
</body>
</html>
</#macro>

<#macro menuFooterPage>
    <@genericPage>
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="<@spring.url '/'/>">Nemocnica Local</a>
            </div>
            <div class="navbar-header pull-right">
                <form action="<@spring.url '/logout'/>" method="post">
                    <input type="submit" value="Logout"/>
                </form>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="<@spring.url '/admin'/>">Admin</a></li>
                    <li><a href="#">Link1</a></li>
                    <li><a href="#">Link2</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                           aria-expanded="false">Dropdown
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="#">Action1</a></li>
                            <li><a href="#">Action2</a></li>
                            <li><a href="#">Action3</a></li>
                            <li class="divider"></li>
                            <li class="dropdown-header">Nav header</li>
                            <li><a href="#">Separated link</a></li>
                            <li><a href="#">One more separated link</a></li>
                        </ul>
                    </li>
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
                <a class="navbar-brand" href="<@spring.url '/'/>">Nemocnica Local</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">

                    <li><a href="#">Dashboard</a></li>
                    <li><a href="#">Settings</a></li>
                    <li><a href="#">Profile</a></li>
                    <li><a href="#">Help</a></li>
                </ul>
                <form class="navbar-form navbar-right">
                    <input type="text" class="form-control" placeholder="Search...">
                </form>
            </div>
        </div>
    </nav>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-sidebar">
                    <li><a href="<@spring.url '/admin'/>">Overview <span class="sr-only">(current)</span></a></li>
                    <li><a href="<@spring.url '/admin/zamestnanci'/>">Zamestnanci</a></li>
                    <li><a href="#">Reports</a></li>
                    <li><a href="#">Analytics</a></li>
                    <li><a href="#">Export</a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li><a href="">Nav item</a></li>
                    <li><a href="">Nav item again</a></li>
                    <li><a href="">One more nav</a></li>
                    <li><a href="">Another nav item</a></li>
                    <li><a href="">More navigation</a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li><a href="">Nav item again</a></li>
                    <li><a href="">One more nav</a></li>
                    <li><a href="">Another nav item</a></li>
                </ul>
            </div>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <#nested>
            </div>
        </div>
    </div>
    </@genericPage>
</#macro>