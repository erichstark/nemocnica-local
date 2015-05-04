<#import "../../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<@pt.dashboardPage>
<h1 class="page-header">Administracia poistovni</h1>

<div class="row">
    <div class="col-md-12">
        <a class="btn btn-info btn-sm" href="<@spring.url '/admin/poistovna/add'/>" role="button">Vytvoriť
            poistovnu</a>
    </div>
</div>

<h2 class="sub-header">Poistovne</h2>

<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th style="width: 60px;">#</th>
            <th style="width: 60px;">ID</th>
            <th>Názov poistovne</th>
            <th style="width: 60px;">Akcia</th>
        </tr>
        </thead>
        <tbody>
            <#list poistovne as poistovna>
            <tr>
                <td>${poistovna_index + 1}</td>
                <td>${poistovna.id}</td>
                <td><a href="<@spring.url '/admin/poistovna/edit/' + poistovna.id />">${poistovna.nazov}</a></td>
                <td><a href="<@spring.url '/admin/poistovna/delete/' + poistovna.id />"
                       onclick="return confirm('!! Zmaz ak si si isty ze tato poistovna nema ziadnu ambulanciu !!');">Zmazať</a>
                </td>
            </tr>
            </#list>
        </tbody>
    </table>
</div>
</@pt.dashboardPage>