require 'buildr/git_auto_version'
require 'buildr/gpg'
require 'buildr/gwt'

desc 'GWT WebSocket Library'
define 'gwt-websockets' do
  project.group = 'org.realityforge.gwt.websockets'
  compile.options.source = '1.8'
  compile.options.target = '1.8'
  compile.options.lint = 'all'

  project.version = ENV['PRODUCT_VERSION'] if ENV['PRODUCT_VERSION']

  pom.add_apache_v2_license
  pom.add_github_project('realityforge/gwt-websockets')
  pom.add_developer('realityforge', 'Peter Donald')
  pom.provided_dependencies.concat [:javax_annotation, :gwt_user]

  compile.with :javax_annotation, :gwt_user

  gwt(%w(org.realityforge.gwt.websockets.WebSockets),
      :java_args => %w(-Xms512M -Xmx1024M),
      :draft_compile => 'true') unless ENV['GWT'] == 'no'

  test.using :testng
  test.with :mockito

  package(:jar).include("#{_(:source, :main, :java)}/*")
  package(:sources)
  package(:javadoc)
end
