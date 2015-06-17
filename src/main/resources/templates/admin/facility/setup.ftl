<#import "../../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<#assign pageTitle>Inštalácia systému</#assign>
<@pt.genericPage pageTitle=pageTitle>
<div class="container">
    <h1>${pageTitle}</h1>

    <div id="alertContainer">
    </div>
    <form id="facility-setup" name="facility" method="post">
        <span class="text-muted" style="padding-bottom: 10px">* Povinné údaje</span>

        <div class="form-group">
            <label for="name">Názov*</label>
            <input type="text" name="name" class="form-control" id="name" required>
        </div>
        <div class="form-group">
            <label for="streetAndNumber">Ulica a číslo</label>
            <input type="text" name="streetAndNumber" class="form-control" id="streetAndNumber">
        </div>
        <div class="form-group">
            <label for="zip">PSČ</label>
            <input type="text" name="zip" class="form-control" id="zip">
        </div>
        <div class="form-group">
            <label for="city">Mesto</label>
            <input type="text" name="city" class="form-control" id="city">
        </div>

        <h3>Parametre synchronizácie s Globálnym serverom</h3>

        <div class="form-group">
            <label for="clientID">Client ID*</label>
            <input type="text" name="clientID" class="form-control" id="clientID" required>
        </div>
        <div class="form-group">
            <label for="clientSecret">Client Secret*</label>
            <input type="text" name="clientSecret" class="form-control" id="clientSecret" required>
        </div>
        <div class="form-group">
            <label for="username">Meno*</label>
            <input type="text" name="username" class="form-control" id="username" required>
        </div>
        <div class="form-group">
            <label for="password">Heslo*</label>
            <input type="text" name="password" class="form-control" id="password" required>
        </div>
        <div class="form-group">
            <div>
                <input type="submit" value="Odoslať" class="btn btn-success">
            </div>
        </div>
    </form>
    <div id="spinner" class="spinner">
        <div class="spinner-container container1">
            <div class="circle1"></div>
            <div class="circle2"></div>
            <div class="circle3"></div>
            <div class="circle4"></div>
        </div>
        <div class="spinner-container container2">
            <div class="circle1"></div>
            <div class="circle2"></div>
            <div class="circle3"></div>
            <div class="circle4"></div>
        </div>
        <div class="spinner-container container3">
            <div class="circle1"></div>
            <div class="circle2"></div>
            <div class="circle3"></div>
            <div class="circle4"></div>
        </div>
    </div>
    <div id="success">
        <h3>Inštalácia prebehla úspešne.</h3>
        <a href="<@spring.url '/'/>" class="btn btn-default btn-lg">
            <span class="glyphicon glyphicon-home"></span> Home
        </a>
    </div>
</div>
</@pt.genericPage>