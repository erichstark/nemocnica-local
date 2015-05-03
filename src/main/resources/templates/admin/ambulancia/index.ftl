<#import "../../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<@pt.dashboardPage>
<h1 class="page-header">Administracia ambulancií</h1>

<div class="row">
    <div class="col-md-12">
        <a class="btn btn-info btn-sm" href="<@spring.url '/admin/ambulancia/add'/>" role="button">Vytvoriť
            ambulanciu</a>
    </div>
</div>

<h2 class="sub-header">Ambulancie</h2>

<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th style="width: 60px;">#</th>
            <th style="width: 60px;">ID</th>
            <th>Názov ambulancie</th>
            <th>Názov zariadenia</th>
            <th>Adresa zariadenia</th>
            <th style="width: 60px;">Akcia</th>
        </tr>
        </thead>
        <tbody>
            <#list ambulancie as ambulancia>
            <tr>
                <td>${ambulancia_index + 1}</td>
                <td>${ambulancia.id}</td>
                <td><a href="<@spring.url '/admin/ambulancia/edit/' + ambulancia.id />">${ambulancia.name}</a></td>
                <td>${ambulancia.zariadenie.nazov}</td>
                <td>${ambulancia.zariadenie.mesto+', '+ambulancia.zariadenie.ulica+' '+ambulancia.zariadenie.cislo}</td>
                <td><a href="<@spring.url '/admin/ambulancia/delete/' + ambulancia.id />"
                       onclick="return confirm('Naozaj chcete zmazať záznam?');">Zmazať</a></td>
            </tr>
            </#list>
        </tbody>
    </table>
</div>
</@pt.dashboardPage>