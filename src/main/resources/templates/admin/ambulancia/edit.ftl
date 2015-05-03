<#import "../../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<@pt.dashboardPage>
<h1 class="page-header">Pridanie novej ambulancie</h1>

<div class="row">
    <div class="col-md-12">
        <a class="btn btn-info btn-sm" href="<@spring.url '/admin/ambulancia'/>" role="button">Back</a>
    </div>
</div>

<br>

<div class="table-responsive">

    <form name="ambulancia" action="<@spring.url '/admin/ambulancia/save'/>" method="post">
        <div class="form-group">
            <label for="ambulancia-id">ID</label>
            <input type="text" name="id" class="form-control" id="ambulancia-id" placeholder="ID"
                   value="${ambulancia.id}">
        </div>
        <div class="form-group">
            <label for="ambulancia-name">Názov</label>
            <input type="text" name="name" class="form-control" id="ambulancia-name" placeholder="Name"
                   value="${ambulancia.name!""}">
        </div>
        <div class="form-group">
            <label for="ambulancia-zariadenie">Zariadenie</label>
            <select name="id_zariadenie" class="form-control" id="ambulancia-zariadenie">
                <#list zariadenia as zariadenie>
                    <#if zariadenie.id == ambulancia.zariadenie.id>
                        <option value="${zariadenie.id}" selected="selected">${zariadenie.nazov}</option>
                    <#else>
                        <option value="${zariadenie.id}">${zariadenie.nazov}</option>
                    </#if>
                </#list>
            </select>
        </div>

        <div class="form-group">
            <div>
                <input type="submit" value="Ulož" class="btn btn-success">
            </div>
        </div>

    </form>
</div>
</@pt.dashboardPage>