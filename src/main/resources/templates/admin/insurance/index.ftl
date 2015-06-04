<#import "../../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<#assign pageTitle>Administracia poistovní</#assign>
<@pt.dashboardPage pageTitle=pageTitle>
<div class="row">
    <div class="col-md-12">
        <a class="btn btn-info btn-sm" href="<@spring.url '/admin/insurance/add'/>" role="button">Vytvoriť
            poisťovnu</a>
    </div>
</div>

<h2 class="sub-header">Poisťovne</h2>

<div class="row">
    <div class="col-md-12">
        <form class="form-inline" method="POST" action="<@spring.url '/admin/insurance/search'/>">
            <div class="form-group">
                <label for="text">Vyhľadanie:</label>
                <input type="text" name="text" class="form-control" id="text" placeholder="Hľadaný text"
                       value="${search!""}">
            </div>
            <input type="submit" value="Hľadaj" class="btn btn-default">
            <a class="btn btn-default" href="<@spring.url '/admin/insurance/clear'/>">Zruš</a>
        </form>
    </div>
</div>

<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th style="width: 60px;">#</th>
            <th style="width: 60px;">ID</th>
            <th>Názov poisťovne</th>
            <th style="width: 60px;">Akcia</th>
        </tr>
        </thead>
        <tbody>
            <#list insurances as insurance>
            <tr>
                <td>${insurance_index + 1}</td>
                <td>${insurance.id}</td>
                <td><a href="<@spring.url '/admin/insurance/edit/' + insurance.id />">${insurance.name}</a></td>
                <td><a href="<@spring.url '/admin/insurance/delete/' + insurance.id />"
                       onclick="return confirm('!! Zmaz ak si si isty ze tato insurance nema ziadnu ambulanciu !!');">Zmazať</a>
                </td>
            </tr>
            </#list>
        </tbody>
    </table>
</div>
</@pt.dashboardPage>